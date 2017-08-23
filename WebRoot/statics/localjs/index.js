
//点击登录的方法
$("#loginBtn").click(function(){
	//初始化变量
	var user = new Object();
	//登录账号去除两端空白部分
	user.loginCode=$.trim($("#loginCode").val());
	//登录密码去除两端空白部分
	user.password=$.trim($("#password").val());
	//初始化用户为“启用”状态
	user.isStart=1;
	
	if(user.loginCode=="" || user.loginCode==null){ //验证登录账号为空
		$("#loginCode").focus();//集中焦点
		$("#formtip").css("color","red");//列表的“formtip”表单错误提示，改变样式
		$("#formtip").html("Sorry,登录账号不能为空！");//错误提示内容
	}else if(user.password=="" || user.password==null){//验证登录密码为空
		$("#password").focus();//集中焦点
		$("#formtip").css("color","red");//列表的“formtip”表单错误提示，改变样式
		$("#formtip").html("Sorry,登录密码不能为空！");//错误提示内容
	}else{
		$("#formtip").html("");//清空错误信息
		//异步无刷新技术实现登录
		$.ajax({
			type:"POST",//请求的方法
			url:"/login",//请求的url
			//data:{user:JSON.stringify(user)},//传递的参数
			data:{loginCode:loginCode,password:password},
			dataType:"html",//返回的参数数据类型
			timeout:1000,//请求超时时间
			success:function(data){
				if(data != "" && data=="success"){
					window.location.href="/main";//请求进入主页面
				}else if(data=="failed"){
					$("#formtip").css("color","red");
					$("#formtip").html("账号或密码错误，登录失败，请重新输入！");
					//清空输入值
					$("#loginCode").val("");
					$("#password").val("");
				}else if(data=="nologincode"){
					$("#formtip").css("color","red");
					$("#formtip").html("登录账号不存在，请重试。");
				}else if(data=="nodata"){
					$("#formtip").css("color","red");
					$("#formtip").html("对不起，没有任何数据需要处理！请重试。");
				}else if(data=="pwderror"){
					$("#formtip").css("color","red");
					$("#formtip").html("登录密码错误，请重试。");
				}
			},
			error:function(){
				$("#formtip").css("color","red");
				$("#formtip").html("账号或密码错误，登录失败，请重新输入！");
			}
		});
	}
	
});