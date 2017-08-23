/**
 * 留言模块js
 * author:weilizong
 * date:2017-07-24
 */

 var replyFormState;
 var replyFormId;
    //添加留言
function addMessage(){
    	var formData={
    		messageContent:$("#messageContent").val(),
    	};
    	$(".addMessage").html("正在提交...");
    	$.ajax({
    		url:"/salesystem/message/addmessage.html",
    		type:"post",
    		data:formData,
    		dataType:"json",    		
    		success:function(savedata){
    			if(savedata.addMessageRet=="success"){
    				$(".addMessage").html("提交");
    				var messagerow='<div class="panel panel-success" style="box-shadow: 0px 0px 4px #ccc;border: solid 1px #CCC;margin-bottom:10px; padding:10px;" id="'
    					+savedata.leaveMessage.id+'">' 
    				+'<div class="panel-body"><h4>'+savedata.leaveMessage.createdBy+'&nbsp;留言:'
    				+'</h4>'+ savedata.leaveMessage.messageContent+' <span class="pull-right">'+savedata.leaveMessage.createTime+'</span></div>';
    				+'</div>';
    				$("#messagetitle").after(messagerow);
    				$(".ret").html("留言成功");
    			}else if(savedata.addMessageRet=="fail"){
    				$(".addMessage").html("提交");
    				$(".ret").html("留言提交失败,请重试");
    			}
    		}
    	});
    }
    
    //显示回复留言表单
	$(".reply").click(function(){
		var ob=$(this).parent().parent().parent();
		var mid=ob.attr("id");
		var replyForm='<div id="replyForm" style="margin:10px 0px 0px 0px;padding:10px 0px 0px 20px;">'
			+'<input style="width:480px;" type="text" class="form-control" id="replyContent" name="replyContent" placeholder="请输入回复内容">'
			+'<button class="btn btn-primary" onclick="addReply('+mid+')">提交</button></div>';
		if(replyFormState){
			$("#replyForm").remove();
			replyFormState=false;
			if(mid!=replyFormId){
				ob.append(replyForm);
				replyFormState=true;
				replyFormId=mid;
			}
		}else{
			ob.append(replyForm);
			replyFormState=true;
			replyFormId=mid;
		}
	});
    
	function addReply(mid){
		if($("#replyContent").val()==""|$("#replyContent").val()==null){
			alert("请输入回复内容!");
			return;
		}
		var replyData={
			replyContent:$("#replyContent").val(),
			messageId:mid,
		};
		var ob=$("#"+mid);
		$.ajax({
			url:"/salesystem/message/addReply.html",
    		type:"post",
    		data:replyData,
    		dataType:"json",
    		success:function(redata){
        		if(redata.addReplyRet=="success"){
        			var reply='<div class="panel-body" style="border-top: solid 1px #CCC;margin:10px 0px 0px 0px;padding:10px 0px 0px 20px;">'
        				+'<h4>'+redata.reply.createdBy+'&nbsp;回复:</h4>' 
        				+redata.reply.replyContent
        				+'<span class="pull-right">'+redata.reply.createTime+'</span></div>';
        			$("#replyForm").remove();	
        			ob.append(reply);
        		}
        	}
		});
	}
    
    //删除留言
	$(".deleteMessage").click(function(){
		var ob=$(this).parent().parent().parent();
		var mid=ob.attr("id");
    	if(confirm("你确定要删除id为"+mid+"的留言吗?")){
    		$.ajax({
    			url:"/salesystem/message/delmessage.html",
        		type:"post",
        		data:{id:mid},
        		dataType:"json",
        		success:function(redata){
            		if(redata.delMessageRet=="success"){
            			ob.remove();
            		}
            	}
    		});
    	}
	});
    