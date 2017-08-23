
/**
 * 全局变量
 * */
var path=$("#path").val();

/**
 * 点击“添加商品套餐”
 * */
$('.addGoodsPack').click(function(e){
	window.location.href=path+"/backend/addgoodspack.html";
});

/**
 * 点击“更新商品套餐”
 * */
$('.modifygoodspack').click(function(e){
	var id = $(this).attr('id');
	window.location.href=path+"/backend/modifygoodspack.html?id="+id;
});

/**
 * 引入富文本编辑器
 * */
$("#a_note").cleditor();
$("#m_note").cleditor();

/**
 * 点击“添加商品套餐”按钮
 * */
/*$('.addGoodsPack').click(function(e){
	//window.location.href=path+"/backend/addgoodspack.shtml";
	$("#add_formtip").html("");
	e.preventDefault();
	$("#addGoodsPackDiv").modal("show");
	
});*/

/**
 * 点击商品套餐添加弹窗的“取消”按钮
 * */
/*$(".addgoodspackcancel").click(function(e){
	$("#add_formtip").html("");
	$("#a_goodsPackName").val("");
	$("#a_goodsPackCode").val("");
	$("#a_num").val("");
	$("#a_totalPrice").val("");
	$("#a_note").val("");
});*/

/**
 * 套餐类型选择
 * */
/*$("#select_a_typeId").change(function(){
	$("#select_a_typeName").val($("#select_a_typeId").find("option:selected").text()) ;
});

$("#m_typeId").change(function(){
	$("#m_typeName").val($("#m_typeId").find("option:selected").text()) ;
});
*/
/**
 * 添加项：选择相关商品列表
 * */
/*$("#selectgoodslist").change(function(){
	var totleprice = 0;
	$(".finalresult").each(function () {  
		id = $(this).attr("goodsid");
		rprice = $(this).attr("rprice");
		gcount = $(this).val();
		totleprice = parseInt(totleprice) + parseInt(rprice*gcount);
    });  
	$("#a_totalPrice").val(totleprice);
});*/

/**
 * 添加项：验证套餐编码是否存在
 * */
/*$("#a_goodsPackCode").blur(function(){
	var agpc = $.trim($("#a_goodsPackCode").val());
	if(agpc != ""){
		$.post(path+"/backend/goodspackcodeisexit.shtml",{'goodsPackCode':agpc,'id':'-1'},function(result){
			if(result == "repeat"){
				$("#add_formtip").css("color","red");
				$("#add_formtip").html("<li>对不起，该套餐编码已存在。</li>");
				$("#add_formtip").attr("key","1");
				result = false;
			}else if(result == "failed"){
				alert("操作超时!");
			}else if(result == "only"){
				$("#add_formtip").css("color","green");
				$("#add_formtip").html("<li>该套餐编码可以正常使用。</li>");
				$("#add_formtip").attr("key","0");
			}
		},'html');
	}
});*/

/**
 * 修改项：验证套餐编码是否存在
 * */
/*$("#m_goodsPackCode").blur(function(){
	var mgpac=$.trim($("#m_goodsPackCode").val());
	if(""!=mgpac){
		//验证套餐编码 是否存在的aJAX
		$.post(path+"/backend/goodspackcodeisexit.shtml",{'goodsPackCode':mgpac,'id':$("m_id").val()},function(result){
			if("repeat"==result){
				$("#modify_formtip").css("color","red");
				$("#modify_formtip").html("<li>对不起，该套餐编码已存在！</li>");
				$("#modify_formtip").attr("key","1");
			}else if("failed"==result){
				alert("工作已超时！");
			}else if("only"==result){
				$("#modify_formtip").css("color","green");
				$("#modify_formtip").html("<li>该套餐编码可以使用！</li>");
				$("#modify_formtip").attr("key","0");
			}
		},"html");
	}
});*/

/**
 * 添加项：商品套餐表单自验证的方法
 * */
/*function addGoodsPackFunc(){
	$("#add_formtip").html("");
	var result = true;
	json = "[";
	$(".finalresult").each(function () {  
		id = $(this).attr("goodsid");
		gcount = $(this).val();
		json = json+"{";
		json = json+"\"goodsInfoId\":\""+id+"\",\"goodsNum\":\""+gcount+"\"";
		json = json+"},";
    });  
	json = json+"{";
	json = json+"\"goodsInfoId\":\"0\",\"goodsNum\":\"0\"";
	json = json+"}";
	json = json + "]";
	$("#goodsJson").val(json);
	//验证套餐名称 
	if($.trim($("#a_goodsPackName").val()) == "" || $("#a_goodsPackName").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，套餐名称不能为空。</li>");
		result = false;
	}
	//验证套餐编码 
	if($.trim($("#a_goodsPackCode").val()) == "" || $("#a_goodsPackCode").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，套餐编码不能为空。</li>");
		result = false;
	}else{
		if($("#add_formtip").attr("key") == "1"){
			$("#add_formtip").append("<li>对不起，该套餐编码已存在。</li>");
			result = false;
		}
	}
	//验证套餐类型
	if($("#a_typeId").val() == ""){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，套餐类型不能为空。</li>");
		result = false;
	}
	//验证库存量
	if($.trim($("#a_num").val()) == "" || $("#a_num").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，套餐库存量不能为空。</li>");
		result = false;
	}
	//验证套餐总价
	if($.trim($("#a_totalPrice").val()) == "" || $("#a_totalPrice").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，套餐总价不能为空。</li>");
		result = false;
	}
	
	return result;
}*/

/**
 * 添加项：添加商品套餐的方法(添加相关商品列表)
 * */
/*function addGoods(id,goodsname,rprice){
//	alert(id+"_"+ goodsname +"_"+ rprice+" _");
	var ok = true;
	$(".goodsname").each(function () {  
		title = $(this).html();
        if(goodsname == title){
        	ok = false;
        	return false;
        }
    });  
	if(ok){
		str = "<div id=\"selectdiv\"><label class=\"goodsname\">"+goodsname+"</label>"+
				"<label class=\"goodscount\"><input class=\"finalresult\" goodsid=\""+id+"\" rprice=\""+rprice+"\" type=\"text\" value=\"1\"/></label>"+
						"<label class=\"del\" rprice=\""+rprice+"\"><img src=\"/statics/img/cancel-on.png\"/></label>"+
						"<label class=\"clear\"></label></div>";
		$("#selectgoodslist").append(str);
		var gcount = $(".finalresult").val();
		$(".del").val(gcount);
		var tprice = rprice*gcount;
		var tempprice = $("#a_totalPrice").val();
		$("#a_totalPrice").val(tprice);
		if(tempprice == "" || tempprice == null) tempprice =0;
		tprice = parseInt(tprice) + parseInt(tempprice);
		console.log("tprice------ "+tprice);
		$("#a_totalPrice").val(tprice);
		$('.del').click(function(e){
			$(this).parents("#selectdiv").remove();
			$("#selectgoodslist").change();
		});
	}
}
*/

/**
 * 选择相关商品列表的“取消”按钮被点击
 * */
/*$('.del').click(function(e){
	$(this).parents("#selectdiv").remove();
	$("#selectgoodslist").change();
});*/

/**
 * 修改商品套餐弹窗的点击“取消”按钮
 * */
/*$('.modifygoodspackcancel').click(function(e){
	$("#modify_formtip").html('');
});*/

/**
 * 修改项：选择相关商品列表
 * */
/*$("#selectgoodslist").change(function(){
	var totleprice = 0;
	$(".finalresult").each(function () {  
		id = $(this).attr("goodsid");
		rprice = $(this).attr("rprice");
		gcount = $(this).val();
		totleprice = parseInt(totleprice) + parseInt(rprice*gcount);
    });  
	$("#m_totalPrice").val(totleprice);
});
*/

/**
 * 点击“修改”按钮
 * */
/*$('.modifygoodspack').click(function(e){
	//$("#add_formtip").html("");
//	e.preventDefault();
//	$("#modifyGoodsPackDiv").modal("show");
	var mid = $(this).attr('id');
	
	//window.location.href=path+"/backend/modifygoodspack.shtml?id="+id;
	//根据id获取商品套餐的ajax
	$.ajax({
		url:path+"/backend/getGoodsPack.shtml",
		type:"POST",
		data:{id:mid},
		dataType:"html",
		timeout:1000,
		error:function(){
			alert("您的操作出现错误啦！");
		},
		success:function(result){
			if("failed"==result){
				alert("工作超时啦!");
			}else if("nodata"==result){
				alert("没有数据！");
			}else{
				//获取结果对象
				var m=eval('('+result+')');
				//显示对应标签的值
				$("#m_id").val(m.id);
				$("#m_goodsPackName").val(m.goodsPackName);
				$("#m_goodsPackCode").val(m.goodsPackCode);	
				//处理套餐类型	
  			    $("#m_typeName").val(m.typeName);
				$("#m_typeId").val(m.typeId);
//				var typeName=m.typeName;
//				var typeId=m.typeId;			
//				if(typeId==null || typeId=="")
//					$("#m_typeId").append("<option value=\""+typeId+"\" selected=\"selected\">--请选择--</option>");
//				for(var i=0;i<cartTypeListJson.length-1;i++){
//					if(cartTypeListJson[i].valueId==typeId){
//						$("#m_typeId").append("<option value=\""+typeId+"\" selected=\"selected\">"+typeName+"</option>");
//					}else{
//						$("#m_typeId").append("<option value=\""+cartTypeListJson[i].valueId+"\">"+cartTypeListJson.valueName+"</option>");
//					}
//				}
				//获取库存量
				$("#m_num").val(m.num);
				$("#m_totalPrice").val(m.totalPrice);
				//获取商品状态
	//			$("#m_state").val(m.state);
				$("#m_state").html("");
				var state=m.state;
				if(state == 1){
					$("#m_state").append("<span>状态：</span><input type=\"radio\" name=\"state\" checked=\"checked\" value=\"1\"/>上架<input type=\"radio\" name=\"state\" value=\"2\"/>下架");					
				}else if(state == 2){
				    $("#m_state").append("<span>状态：</span><input type=\"radio\" name=\"state\" value=\"1\"/>上架<input type=\"radio\" name=\"state\" checked=\"checked\" value=\"2\"/>下架");
				}
				//获取商品说明
			//	$("#m_note").val(m.note);
				$("#m_note").html("");
				$("#m_note").append("<span>套餐说明:</span> <br /><textarea id=\"m_note\" name=\"note\" rows=\"3\">"+m.note+"</textarea>");
				$("#m_note").cleditor();
				
				e.preventDefault();
				$("#modifyGoodsPackDiv").modal("show");
			}
		}
	});
});*/

/**
 * 修改项：修改表单提交前自验证的方法
 * */
/*function modifyGoodsPackFunc(){
	$("#modify_formtip").html("");
	var result = true;
	json = "[";
	$(".finalresult").each(function () {  
		id = $(this).attr("goodsid");
		gcount = $(this).val();
		json = json+"{";
		json = json+"\"goodsInfoId\":\""+id+"\",\"goodsNum\":\""+gcount+"\"";
		json = json+"},";
    });  
	json = json+"{";
	json = json+"\"goodsInfoId\":\"0\",\"goodsNum\":\"0\"";
	json = json+"}";
	json = json + "]";
	$("#goodsJson").val(json);
	
	if( $.trim($("#m_goodsPackName").val()) == "" || $("#m_goodsPackName").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，套餐名称不能为空。</li>");
		result = false;
	}
	if( $.trim($("#m_goodsPackCode").val()) == "" || $("#m_goodsPackCode").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，套餐编码不能为空。</li>");
		result = false;
	}else{
		if($("#modify_formtip").attr("key") == "1"){
			$("#modify_formtip").append("<li>对不起，该套餐编码已存在。</li>");
			result = false;
		}
	}
	if($("#m_typeId").val() == ""){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，套餐类型不能为空。</li>");
		result = false;
	}
	if( $.trim($("#m_num").val()) == "" || $("#m_num").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，套餐库存量不能为空。</li>");
		result = false;
	}
	if( $.trim($("#m_totalPrice").val()) == "" || $("#m_totalPrice").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，套餐总价不能为空。</li>");
		result = false;
	}
	
	return result;
}*/

/**
 * 点击"查看"按钮
 * */
$('.viewgoodspack').click(function(e){
	
	var vid = $(this).attr('id');
	//window.location.href=path+"/backend/viewgoodspack.shtml?id="+id;
	//window.location.href=path+"/backend/goodspacklist.html";
	//根据id获取商品套餐的ajax
	$.ajax({
		url:path+"/backend/getGoodsPack.shtml",
		type:"POST",
		data:{id:vid},
		dataType:"html",
		error:function(){
			alert("错误，你无法查看该商品套餐！");
		},
		success:function(result){
			if("failed"==result){
				alert("操作超时啦!");
			}else if("nodata"==result){
				alert("没有数据存在！");
			}else{
				//获取结果对象
				m=eval('('+result+')');
				$("#v_goodsPackName").val(m.goodsPackName);
				$("#v_goodsPackCode").val(m.goodsPackCode);
				$("#v_typeName").val(m.typeName);
				$("#v_num").val(m.num);
				$("#v_totalPrice").val(m.totalPrice);
				//判断状态
				var state=m.state;
				if(state==1){
					$("#v_state").html("上架");
				}else if(state==2){
					$("#v_state").html("下架");
				}
				//获取商品说明
				$("#v_note").val(m.note);
				e.preventDefault();
				$("#viewGoodsPackDiv").modal("show");//弹窗
			}
		}
	});
});

/**
 * 点击“删除”按钮
 * */
$('.delgoodspack').click(function(e){
	var d_id = $(this).attr('id');
	var goodsPackName = $(this).attr('goodsPackName');
	if(confirm("您确定要删除【"+goodsPackName+"】这个商品套餐吗？")){
		//实现删除的ajax
		$.post(path+"/backend/delgoodspack.shtml",{'delId':d_id},function(result){
			if("success" == result){
				alert("删除成功！");
				window.location.href=path+"/backend/goodspacklist.html";
			}else{
				alert("删除失败！");
			}
		},'html');
	}
	
	
});

/**
 * 点击“修改状态”,"直接勾选修改状态，立即生效"
 * */
$(".modifystate").click(function(){
	var modify = $(this);
	var state= modify.attr("state");
	var id= modify.attr("goodspackid");
	goodsPackStart = new Object();
	goodsPackStart.id = id;
	if(state == "1"){
		goodsPackStart.state = 2;
		modify.attr("state","2");
	}
	else{
		goodsPackStart.state = 1;
		modify.attr("state","1");
	}
	//关修改状态的ajax
	$.ajax({
		url: path+'/backend/modifygoodspackstate.shtml',
		type: 'POST',
		data:{state:JSON.stringify(goodsPackStart)},
		dataType: 'html',
		timeout: 1000,
		error: function(){
			alert("上架或下架商品套餐操作时失败！请重试。");
		},
		success: function(result){
			if(result != "" && "success" == result){
				if(state == "1")
					modify.attr("state",2);
				else
					modify.attr("state",1);
			}else if("failed" == result){
				alert("上架或下架商品套餐操作时失败！请重试。");
			}else if("nodata" == result){
				alert("对不起，没有任何数据需要处理！请重试。");
			}
		}
	});
});

