/**
 * 资讯模块的js
 * author:weilizong
 * date:2017-07-22
 */
   //资讯模态弹窗
    $(".add").click(function(){
    	$("#addInforDiv").modal();
    });
    
    //添加资讯
    $(".saveInfor").click(function(){
    	//var formData=new FormData($("#addInforForm")[0]);
    	var formData={
    		title:$("#title").val(),
    		content:$("#title2").val(),
    		state:$("#state").val(),
    		typename:$("#typename").val(),
    		filepath:$("#filepath").val(),
    		filename:$("#filename").val(),
    		filesize:$("#filesize").val(),
    		uploadtime:$("#uploadtime").val(),
    	}
    	$(this).val("正在保存...");
    	$.ajax({
    		url:"/salesystem/informanage/saveInfor.html",
    		type:"post",
    		data:formData,
    		dataType:"json",    		
    		success:function(savedata){
    			if(savedata.saveInforRet=="success"){
    				var statebox;
    				$(this).val("保存");
    				if(formData.state==1){
    					statebox='<input type="checkbox" name="state" checked/>';
    				}else{
    					statebox='<input type="checkbox" name="state"/>';
    				}
    				var inforrow='<tr id="infor'+savedata.information.id+'">'
                    +'<td><span class="icon icon-color icon-info"></span></td>'
                    +'<td><a href="">'+formData.title+'</a></td>'
                    +'<td><a href="">'+formData.content+'</a></td>'
                    +'<td>'+savedata.information.filesize+'</td>'
                    +'<td>'+statebox+'</td>'
                    +'<td>'+savedata.information.publishtime+'</td>'
                    +'<td><a href="${pageContext.request.contextPath}/informanage/updateInfor.html?id='+savedata.information.id+'" class="viewInfor" infor=""><span class="glyphicon glyphicon-open" style="color: rgb(27, 167, 60);font-size:14px;">查看</span></a>&nbsp;'
                    +'<a  class="deleteInfor"  inforId="'+savedata.information.id+'"><span class="glyphicon glyphicon-remove" style="color: rgb(213, 66, 60);font-size:14px;">删除</span></a>'
                    +'</td></tr>'
    				$("#inforTable").append(inforrow);
    				$("#addInforDiv").modal("hide");
    			}
    		}
    	});
    });
    
  //添加资讯
    $(".updateInfor").click(function(){
    	var formData={
    		id:$("#inforId").val(),
    		title:$("#title").val(),
    		content:$("#title2").val(),
    		state:$("#state").val(),
    		typename:$("#typename").val(),
    		filepath:$("#filepath").val(),
    		filename:$("#filename").val(),
    		filesize:$("#filesize").val(),
    		uploadtime:$("#uploadtime").val(),
    	}
    	$(this).val("正在保存...");
    	$.ajax({
    		url:"/salesystem/informanage/updateInforToDb.html",
    		type:"post",
    		data:formData,
    		dataType:"json",    		
    		success:function(savedata){
    		//var data=eval("("+savedata+")");
    			if(savedata.upRet=="success"){
    				window.location.href="/salesystem/informanage/information.html";
    			}
    		}
    	});
    });
    
    //上传文件
    $("#uploadInfor").click(function(){
    	var formData=new FormData($("#addInforForm")[0]);
    	$("#result").html("正在上传...");
    	$.ajax({
    		url:"/salesystem/informanage/saveUploadFile.html",
    		type:"post",
    		data:formData,
    		async:false,
    		contentType:false,
    		processData:false,
    		timeout:1000,
    		success:function(redata){
    		var data=eval("("+redata+")");
    			if(data.result=="success"){
    				$("#result").html("上传成功!");
    				$("#filepath").val(data.filePath);
    				$("#typename").val(data.suffix);
    				$("#filename").val(data.filename),
    				$("#filesize").val(data.filesize),
    				$("#uploadtime").val(data.uploadtime),
    				$("#inputInforFile").val("");
    			}else if(data.result=="fail"){
    				$("#result").html(data.uploadFileError);
    			}
    		},
    	error:function(){
    		$("#result").html("上传失败,超时");
    	}
    	});
    });
    
    //查看资讯
    $(".viewInfor").click(function(){
    	$("#vedio").html("");
    	var vedio='<video width="500" height="400" id="vedio" controls>'
    	+'<source src="/salesystem/'+$(this).attr("src")+'" type="video/mp4">'
		+'</video>'; 
    	$("#viewInforDiv .modal-body").html(vedio);
    	$("#viewInforDiv").modal();
    	$('#viewInforDiv').on('hide.bs.modal', function () {
    		  var v=$("#viewInforDiv video").get(0);
                  v.pause();
    	})
    });
    
    //删除资讯
    $(".deleteInfor").click(function(){
    	var obj=$(this);
    	if(confirm("你确定要删除id为"+$(this).attr("inforId")+"的记录吗?")){
    		$.ajax({
    			url:"/salesystem/informanage/deleteInfor.html",
        		type:"post",
        		data:{id:$(this).attr("inforId")},
        		dataType:"json",
        		success:function(redata){
            		if(redata.delRet=="success"){
            			obj.parent().parent().remove();
            		}
            	}
    		});
    	}
    });
    
    