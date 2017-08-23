    var recharge_time=$("#date01");
	var note=$("#textarea2");
	var recharge_money=$("#recharge_money");
	var recharge_num=$("#recharge_num");
	var modalBody=$(".modal-body");
	recharge_time.bind("focus",function(){
		recharge_time.next().html("请选择汇款日期").css("color","#0f0f0f");
	 }).bind("blur",function(){
	    if(recharge_time.val()!=null&&recharge_time.val()!=''){
	    	vallidateTip(recharge_time.next(),{"color":"green"},"OK",true);
	  } else{
		  vallidateTip(recharge_time.next(),{"color":"red"},"日期不能为空",false);
	  }
	 }); 
	    recharge_money.bind("focus",function(){

	    	recharge_money.next().next().html("请输入汇款金额！").css("color","#0f0f0f");
		 }).bind("blur",function(){
		    if(recharge_money.val()!=null&&recharge_money.val()!=''){
		    	vallidateTip(recharge_money.next().next(),{"color":"green"},"OK",true);
		  } else{
			  vallidateTip(recharge_money.next().next(),{"color":"red"},"金额不能为空",false);
		  }
		 }); 
		    recharge_num.bind("focus",function(){	 
		    	recharge_num.next().html("请输入单号").css("color","#0f0f0f");
			 }).bind("blur",function(){
			    if(recharge_num.val()!=null&&recharge_num.val()!=''){
			    	vallidateTip(recharge_num.next(),{"color":"green"},"OK",true);
			  } else{
				  vallidateTip(recharge_num.next(),{"color":"red"},"单号不能为空",false);
			  }
			 }); 		    

$("#btnSubm").click(function(e){
	 if(recharge_time.next().attr("validateStatus")!="true"){
		 recharge_time.blur();
	 }else if(recharge_money.next().next().attr("validateStatus")!="true"){
		 recharge_money.blur();
	 }else if(recharge_num.next().attr("validateStatus")!="true"){
		 recharge_num.blur();
	 }else{
		 alert(1111);
		 modalBody.html('');
			var row='';
			row+="<p>汇款日期："+recharge_time.val()+"</p>";
			row+="<p>汇款金额："+recharge_money.val()+"</p>";
			row+="<p>汇款摘要："+note.val()+"</p>";
			row+="<p>汇款单号："+recharge_num.val()+"</p>";
			modalBody.append(row);
			e.preventDefault;
			$('#addRecharge').modal('show');
	 }
});	    
function subSave(){
	$("#formid").submit();
}

function vallidateTip(element,css,tipString,status){
	  element.css(css);
	  element.html(tipString);
	  element.attr("validateStatus",status);
  }
