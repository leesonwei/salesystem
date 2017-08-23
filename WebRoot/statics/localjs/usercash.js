$('#cashtip').modal('show');
var cash_money=$("#cash_money");
var bank_brance=$("#bank_brance");
var bank_name=$("#bank_name");
var account_name=$("#account_name");
var bank_account=$("#bank_account");
var base_balance=$("#base_balance");
var modalBody=$(".modal-body");


  
    cash_money.bind("focus",function(){
	      cash_money.next().html("(提现金额不能大于余额)").css("color","red");
	 }).bind("blur",function(){
	    if(cash_money.val()!=null&&cash_money.val()!=''&&parseInt(base_balance.val())>=parseInt(cash_money.val())){
	    	vallidateTip(cash_money.next(),{"color":"green"},"OK",true);	    	
	  }else{
		  vallidateTip(cash_money.next(),{"color":"red"},"提现金额不能为空或大于余额",false);
	  }
	 });
  
    bank_brance.bind("focus",function(){
    	bank_brance.next().html("请输入银行所在地址及支行详细名称！").css("color","#e7c3c3");
			 }).bind("blur",function(){
			    if(bank_brance.val()!=null&&bank_brance.val()!=''){
			    	vallidateTip(bank_brance.next(),{"color":"green"},"OK",true);
			  } else{
				  vallidateTip(bank_brance.next(),{"color":"red"},"详细名称必能为空",false);
			  }   
			 });    
	 bank_name.bind("focus",function(){
			    	bank_name.next().html("请选择银行！").css("color","#e7c3c3");
	               }).bind("blur",function(){
			      if(bank_name.val()!=null&&bank_name.val()!=''){
				    vallidateTip(bank_name.next(),{"color":"green"},"OK",true);
			      }else{
				     vallidateTip(bank_name.next(),{"color":"red"},"银行不能为空",false);
				}   
		 }); 
	 account_name.bind("focus",function(){
		 account_name.next().html("请输入账号姓名").css("color","#e7c3c3");
        }).bind("blur",function(){
	      if(account_name.val()!=null&&account_name.val()!=''){
		    vallidateTip(account_name.next(),{"color":"green"},"OK",true);
	      }else{
		     vallidateTip(account_name.next(),{"color":"red"},"账号姓名不能为空",false);
		}   
}); 
	 bank_account.bind("focus",function(){
		 bank_account.next().html("请输入账号").css("color","#e7c3c3");
        }).bind("blur",function(){
	      if(bank_account.val()!=null&&bank_account.val()!=''){
		    vallidateTip(bank_account.next(),{"color":"green"},"OK",true);
	      }else{
		     vallidateTip(bank_account.next(),{"color":"red"},"账不能为空",false);
		}   
}); 		    

$("#btnSubm").click(function(e){
 if(cash_money.attr("validateStatus")!="true"){
	 cash_money.blur();
 }else if(bank_brance.attr("validateStatus")!="true"){
	 bank_brance.blur();
 }else if(bank_name.attr("validateStatus")!="true"){
	 bank_name.blur();
 }else if(account_name.attr("validateStatus")!="true"){
	 account_name.blur();
 }else if(bank_account.attr("validateStatus")!="true"){
	 bank_account.blur();
 }else{
	 alert(1111);
	 modalBody.html('');
		var row='';
		row+="<p>提现金额："+cash_money.val()+"（元）</p>";
		row+="<p>银行所在地址及支行详细名称：："+bank_brance.val()+"</p>";
		row+="<p>银行名称："+bank_name.val()+"</p>";
		row+="<p>账户名称："+account_name.val()+"</p>";
		row+="<p>银行卡号："+bank_account.val()+"</p>";
		modalBody.append(row);
		e.preventDefault;
		$('#cashinfo').modal('show');
 }
});    

function subSave(){
	var data=$('#formid').serialize();
$.post("usercashsave",data,function(result){
	debugger;
	if(result=='accounterror'){
		modalBody.html('账户信息有误，请核对清楚！').css('color','red');
	}else if(result=='success'){
		 modalBody.html('提现成功！').css('color','green');
	}else if(result=='logfailure'){
		modalBody.html('提现失败！请重试').css('color','red');
	}else{
		modalBody.html('提现失败！请重试').css('color','red');
	}
 });
}
function vallidateTip(element,css,tipString,status){
  element.css(css);
  element.html(tipString);
  element.prev().attr("validateStatus",status);
}