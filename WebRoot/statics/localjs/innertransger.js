    var password2=$("#password2");
	var transferMoney=$("#transferMoney");
	var loginCode=$("#loginCode");
	var sessionpassword2=$("#sessionpassword2");
	var focusedInput=$("#focusedInput");
	var base_balance=$("#base_balance");
	var user_id=$("#user_id");
	var modalBody=$(".modal-body");
	
	loginCode.bind("focus",function(){	 
		loginCode.next().html("(接收转帐的会员用户名必须填写正确，不能给註册会员转帐。）").css("color","red");
	 }).bind("blur",function(){
	    if(loginCode.val()!=null&&loginCode.val()!=''){
	    	$.post("valloginCode",{loginCode:loginCode.val()},function(result){
	    		 if(result=='yes'){
	    			 vallidateTip(loginCode.next(),{"color":"green"},"可以给该会员转账",true);
	    		 }else{
	    			 vallidateTip(loginCode.next(),{"color":"red"},"该会员不存在！",false);
	    		 }
	    	   });
	    	} else{
	  		  vallidateTip(loginCode.next(),{"color":"red"},"转入会员名不能为空",false);
	  	  }	    	
	  });
	  
	  transferMoney.bind("focus",function(){
		  transferMoney.next().html("请输入汇款金额！").css("color","#0f0f0f");
		 }).bind("blur",function(){
		    if(transferMoney.val()!=null&&transferMoney.val()!=''&&parseInt(base_balance.val())>=parseInt(transferMoney.val())){
		    	vallidateTip(transferMoney.next(),{"color":"green"},"OK",true);
		    	
		  }else if(transferMoney.val()>base_balance.val()){
			  vallidateTip(transferMoney.next(),{"color":"red"},"<a href='/elecbank/recharge.html'>余额不足，马上去汇款充值</a>",false);
		  } else{
			  vallidateTip(transferMoney.next(),{"color":"red"},"金额不能为空",false);
		  }
		 }); 
		    password2.bind("focus",function(){
		    	password2.next().html("请输入二级密码！").css("color","red");
				 }).bind("blur",function(){
				    if(password2.val()!=null&&password2.val()!=''&&password2.val()==sessionpassword2.val()){
				    	vallidateTip(password2.next(),{"color":"green"},"OK",true);
				  } else{
					  vallidateTip(password2.next(),{"color":"red"},"二级密码错误",false);
				  }   
				 });    
		
				    

$("#btnSubm").click(function(e){
	 if(loginCode.attr("validateStatus")!="true"){
		 loginCode.blur();
	 }else if(transferMoney.attr("validateStatus")!="true"){
		 transferMoney.blur();
	 }else if(password2.attr("validateStatus")!="true"){
		 password2.blur();
	 }else{
		 alert(1111);
		 modalBody.html('');
			var row='';
			row+="<p>转账目标会员："+loginCode.val()+"</p>";
			row+="<p>转账金额："+transferMoney.val()+"（元）</p>";
			row+="<p>转账时间："+getNowFormatDate()+"</p>";
			modalBody.append(row);
			e.preventDefault;
			$('#transfer').modal('show');
	 }
});    
/*	$("#btnSubm").click(function(e){
		
			 modalBody.html('');
				var row='';
				row+="<p>汇款日期："+recharge_time.val()+"</p>";
				row+="<p>汇款金额："+recharge_money.val()+"</p>";
				row+="<p>汇款摘要："+note.val()+"</p>";
				row+="<p>汇款单号："+recharge_num.val()+"</p>";
				modalBody.append(row);
				e.preventDefault;
				$('#addRecharge').modal('show');	
});*/
function subSave(){
	$.post("transgerSave",{transferMoney:transferMoney.val(),userin_loginCode:loginCode.val(),userout_id:user_id.val()},function(result){
		if(result='success'){
			 modalBody.html('转账成功！')
			 window.location.href="innerTransferAccount.html";
		}else if(result='failure'){
			modalBody.html('转账失败！请重试')
		}else{
			modalBody.html('转账出错！请重试')
		}
	});
}
function vallidateTip(element,css,tipString,status){
	  element.css(css);
	  element.html(tipString);
	  element.prev().attr("validateStatus",status);
  }
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
