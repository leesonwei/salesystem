$("#btnSave").click(function(){
	var path=$("#path").val();
	  var data=$('form').serialize();
	   $.post(path+"/elecbank/rechargesave",data,function(result){
		   
					if(result=="success"){
						alert("充值成功");
					}else if(result=="addlogerror"){
						alert("充值异常，请重试！");
					}else if(result=="failure"){
						alert("充值失败");
					}else if(result=="nobaseaccount"){
						alert("该用户没有激活基本！");
					}else{
						alert("充值异常，请重试！");
					}
				   
	   });
});