
//全局变量
var path=$("#path").val();

/**
 * 点击“权限列表的名称”标识
 * */
$('.roleNameAuthority').click(function(){
	//获取当前权限对象
	var authority = $(this);
	//获取当前权限对象的属性值roleid="${role.id}
	var roleId = authority.attr("roleid");
	//显示当前权限的标题
	$("#selectrole").html("当前配置角色为：" + authority.attr("rolename"));
	//隐藏角色id值
	$("#roleidhide").val(roleId);
	//ajax关于获取功能列表
	$.ajax({
		url: path+'/backend/functions.shtml',
		type: 'POST',
		data:{fid:roleId},
		dataType: 'html',
		timeout: 1000,
		error: function(){
		},
		success: function(result){
			if(result == "nodata"){
				alert("对不起，功能列表获取失败，请重试。");
			}else{
				//eval() 函数可计算某个字符串，并执行其中的 JavaScript 代码
				var json = eval('(' + result + ')');
				//初始化列表结果变量
				var listr = "";
				
				for(var i=0;i<json.length;i++){
					listr += "<li>";
					listr += "<ul id=\"subfuncul"+json[i].mainFunction.id+"\" class=\"subfuncul\">";
					listr += "<li  class=\"functiontitle\" ><input id='functiontitle"+json[i].mainFunction.id+"' onchange='mainFunctionSelectChange(this,"+json[i].mainFunction.id+");' funcid=\""+json[i].mainFunction.id+"\" type='checkbox' />"+json[i].mainFunction.functionName+"</li>";
					for(j=0;j<json[i].subFunctions.length;j++){
						
						listr += "<li><input onchange='subFunctionSelectChange(this,"+json[i].mainFunction.id+");' funcid=\""+json[i].subFunctions[j].id+"\" type='checkbox' /> "+json[i].subFunctions[j].functionName+"</li>";
					}
					listr += "</ul></li>";
				}
				//显示对应角色的功能列表
				$("#functionList").html(listr);
				
				//获取功能列表是默认值
				$("#functionList :checkbox").each(function () {  
					var checkbox = $(this);
					$.ajax({
						url: path+'/backend/getAuthorityDefault.shtml',
						type: 'POST',
						data:{rid:$("#roleidhide").val(),fid:$(this).attr("funcid")},
						dataType: 'html',
						timeout: 1000,
						error: function(){
						},
						success: function(result){
							if(result == "success"){
								//alert("ok");
								checkbox.attr("checked", true); 
							}else{
								//alert("no");
								checkbox.attr("checked", false);
							}
						}
						});
				});
				
				
			}
		}
		});
});

/**
 * 子功能选择方法
 * */
function subFunctionSelectChange(obj,id){
	if(obj.checked){
		$("#functiontitle"+id).attr("checked", true);  
	}
}

/**
 * 主功能选择
 * */
function mainFunctionSelectChange(obj,id){
	if(obj.checked){
		$("#subfuncul"+id+" :checkbox").attr("checked", true);  
	}else{
		$("#subfuncul"+id+" :checkbox").attr("checked", false);  
	}		
}

/**
 * 点击“全选”按钮
 * */
$("#selectAll").click(function () {//全选  
    $("#functionList :checkbox").attr("checked", true);  
});  

/**
 * 点击“全不选”按钮
 * */
$("#unSelect").click(function () {//全不选  
    $("#functionList :checkbox").attr("checked", false);  
});  

/**
 * 点击“反选”按钮
 * */
$("#reverse").click(function () {//反选  
    $("#functionList :checkbox").each(function () {  
        $(this).attr("checked", !$(this).attr("checked"));  
    });  
});  
 
/**
 * 点击“确定赋予权限”按钮
 * */
$("#confirmsave").click(function(){
	//提示框
	if(confirm("您确定要修改当前角色的权限吗？")){
	
	ids = $("#roleidhide").val()+"-";
	$("#functionList :checkbox").each(function () {
		if($(this).attr("checked") == 'checked'){
			ids += $(this).attr("funcid") + "-" ;
		}
    }); 
	$.ajax({
		url: path+'/backend/modifyAuthority.shtml',
		type: 'POST',
		data:{ids:ids},
		dataType: 'html',
		timeout: 1000,
		error: function(){
		},
		success: function(result){
			if(result == "nodata"){
				alert("对不起，功能列表获取失败，请重试。");
			}else{
				alert("恭喜您，权限修改成功。");
			}
		}
		});
	}
});
