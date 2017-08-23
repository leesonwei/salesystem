/**
 * 资讯模块js
 * author:weilizong
 * date:2017-07-24
 */
   //资讯模态弹窗
    $(".add").click(function(){
    	$("#addAfficheDiv").modal();
    });
    
    //添加资讯
    $(".saveAffiche").click(function(){
    	var formData={
    		title:$("#title").val(),
    		starttime:$("#startTime").val(),
    		endtime:$("#endTime").val(),
    		content:$("#title2").val()
    	};
    	$(this).val("正在保存...");
    	$.ajax({
    		url:"/salesystem/informanage/saveAffiche.html",
    		type:"post",
    		data:formData,
    		dataType:"json",    		
    		success:function(savedata){
    			
    			if(savedata.saveAfficheRet=="success"){
    				$(".saveAffiche").val("保存");
    				var afficherow='<tr>'
                    +'<td><span class="icon icon-color icon-info"></span></td>'
                    +'<td><a href="">'+formData.title+'</a></td>'
                    +'<td>'+savedata.affiche.publisher+'</td>'
                    +'<td>'+savedata.affiche.publishTime+'</td>'
                    +'<td><a href="${pageContext.request.contextPath}/informanage/updateAffiche.html?id='+savedata.affiche.id+'" class="viewInfor" infor=""><span class="glyphicon glyphicon-open" style="color: rgb(27, 167, 60);font-size:14px;">修改</span></a>&nbsp;'
                    +'<a  class="deleteAffiche"  afficheId="'+savedata.affiche.id+'"><span class="glyphicon glyphicon-remove" style="color: rgb(213, 66, 60);font-size:14px;">删除</span></a>'
                    +'</td></tr>'
    				$("#afficheTable").append(afficherow);
    				$("#addAfficheDiv").modal("hide");
    			}
    		}
    	});
    });
    
    
    
    //删除公告
    $(".deleteAffiche").click(function(){
    	var obj=$(this);
    	if(confirm("你确定要删除id为"+$(this).attr("afficheId")+"的记录吗?")){
    		$.ajax({
    			url:"/salesystem/informanage/deleteAffiche.html",
        		type:"post",
        		data:{id:$(this).attr("afficheId")},
        		dataType:"json",
        		success:function(redata){
            		if(redata.delRet=="success"){
            			obj.parent().parent().remove();
            		}
            	}
    		});
    	}
    });
    
    //修改公告
    $(".updateAffiche").click(function(){
    	var obj=$(this);
    	var formData={
    			id:$("#afficheId").val(),
        		title:$("#title").val(),
        		startTime:$("#startTime").val(),
        		endTime:$("#endTime").val(),
        		content:$("#title2").val()
        	};
    		$.ajax({
    			url:"/salesystem/informanage/updateAffiche.html",
        		type:"post",
        		data:formData,
        		dataType:"json",
        		success:function(redata){
            		if(redata.updateRet=="success"){
            			window.location.href="/salesystem/informanage/affiche.html";
            		}
            	}
    		});
    });
    
    