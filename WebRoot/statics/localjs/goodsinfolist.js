
/**
 * 全局变量
 * */
var path=$("#path").val();

/**
 * 富文本编辑器
 * */
$('#a_goodsFormat').cleditor();
$('#a_note').cleditor();

/**
 * 点击“添加商品”按钮，弹窗
 * */
$('.addGoodsInfo').click(function(e){
	$("#add_formtip").html('');
	e.preventDefault();
	$('#addGoodsInfoDiv').modal('show');
});

/**
 * 添加商品弹窗的点击“取消”按钮
 * */
$('.addgoodsinfocancel').click(function(e){
	$("#add_formtip").html('');
	$("#a_goodsName").val('');
	$("#a_goodsSN").val('');
	$("#a_marketPrice").val('');
	$("#a_realPrice").val('');
	$("#a_num").val('');
	$("#a_unit").val('');
	$("#a_goodsFormat").val('');
	$("#a_note").val('');
});

/**
 * 修改商品弹窗的点击“取消”按钮
 * */
$('.modifygoodsinfocancel').click(function(e){
	$("#modify_formtip").html('');
});

/**
 * 点击商品的“修改”按钮，弹窗
 * */
$('.modifygoodsinfo').click(function(e){
	//获取<a>标签上的id="${goodsInfo.id}"属性值
	var m_id = $(this).attr('id');
	//获取根据id获取商品的ajax
	$.ajax({
		url: path+'/backend/getgoodsinfo.shtml',
		type: 'POST',
		data:{id:m_id},
		dataType: 'html',
		timeout: 1000,
		error: function(){
			alert("error");
		},
		success: function(result){
			if("failed" == result){
				alert("操作超时！");
			}else if("nodata" == result){
				alert("没有数据！");
			}else{
				//获取结果对象
				m = eval('(' + result + ')');
				//显示对应标签的值
				$("#m_id").val(m.id);
				$("#m_goodsName").val(m.goodsName);
				$("#m_goodsSN").val(m.goodsSN);
				$("#m_marketPrice").val(m.marketPrice);
				$("#m_realPrice").val(m.realPrice);
				$("#m_num").val(m.num);
				$("#m_unit").val(m.unit);
				//获取状态，判断处理
				$("#m_state").html('');
				var state = m.state; 
				if(state == 1){
					$("#m_state").append("<span>状态：</span><input type=\"radio\" name=\"state\" checked=\"checked\" value=\"1\"/>上架<input type=\"radio\" name=\"state\" value=\"2\"/>下架");					
				}else if(state == 2){
				    $("#m_state").append("<span>状态：</span><input type=\"radio\" name=\"state\" value=\"1\"/>上架<input type=\"radio\" name=\"state\" checked=\"checked\" value=\"2\"/>下架");
				}
				//获取商品规格
				$("#m_goodsFormatli").html("");
				$("#m_goodsFormatli").append("<span>商品规格：</span> <br/><textarea id=\"m_goodsFormat\" name=\"goodsFormat\" rows=\"3\">"+m.goodsFormat+"</textarea>");
				$('#m_goodsFormat').cleditor();
				//获取商品说明
				$("#m_noteli").html("");
				$("#m_noteli").append("<span>商品说明：</span> <br/><textarea id=\"m_note\" name=\"note\" rows=\"3\">"+m.note+"</textarea>");
				$('#m_note').cleditor();
				//弹窗
				e.preventDefault();
				$('#modifyGoodsInfoDiv').modal('show');
			}
		}
	});
});

/**
 * 点击商品的“查看”按钮，弹窗
 * */
$('.viewgoodsinfo').click(function(e){
	//获取<a>标签上的id="${goodsInfo.id}"属性值
	var m_id = $(this).attr('id');
	//获取根据id获取商品的ajax
	$.ajax({
		url: path+'/backend/getgoodsinfo.shtml',
		type: 'POST',
		data:{id:m_id},
		dataType: 'html',
		timeout: 1000,
		error: function(){
			alert("error");
		},
		success: function(result){
			if("failed" == result){
				alert("操作超时！");
			}else if("nodata" == result){
				alert("没有数据！");
			}else{
				//获取结果对象
				m = eval('(' + result + ')');
				$("#v_goodsName").val(m.goodsName);
				$("#v_goodsSN").val(m.goodsSN);
				$("#v_marketPrice").val(m.marketPrice);
				$("#v_realPrice").val(m.realPrice);
				$("#v_num").val(m.num);
				$("#v_unit").val(m.unit);
				//获取状态，判断处理
				var state = m.state;
				if(state == 1)
					$("#v_state").html("上架");
				else if(state == 2)
					$("#v_state").html("下架");
				//获取商品规格
				$("#v_goodsFormat").html(m.goodsFormat);
				//获取商品说明
				$("#v_note").html(m.note);
				//弹窗
				e.preventDefault();
				$('#viewGoodsInfoDiv').modal('show');
			}
		}
	});
});

/**
 * 点击商品的“删除”按钮
 * */
$('.delgoodsinfo').click(function(e){
	//获取相应的<a>标签对象
	var d = $(this);
	//获取该对象上的相应的属性值
	var d_goodsName = d.attr('goodsName');
	var d_id = d.attr('id');
	//带确认和取消的弹窗提示框，判断处理
	if(confirm("您确定要删除【"+d_goodsName+"】这个商品吗？")){
		//根据id删除商品的ajax
		$.post(path+"/backend/delgoodsinfo.shtml",{'delId':d_id},function(result){
			if("success" == result){
				alert("删除成功！");
				window.location.href=path+"/backend/goodsinfolist.html";
			}else if("isused" == result){
				alert("该商品已被商品套餐引用，不能删除！");
			}else{
				alert("删除失败！");
			}
		},'html');
	}
});

/**
 * 添加商品时：验证该商品编码是否存在
 * */
$("#a_goodsSN").blur(function(){
	//去掉两端空白部分
	var agsn = $.trim($("#a_goodsSN").val());
	//输入不为空
	if(agsn != ""){
		//验证商品编码是否存在的ajax
		$.post(path+"/backend/goodsSNisexit.shtml",{'goodsSN':agsn,'id':'-1'},function(result){
			if(result == "repeat"){
				$("#add_formtip").css("color","red");
				$("#add_formtip").html("<li>对不起，该商品编号已存在。</li>");
				$("#add_formtip").attr("key","1");
				result = false;
			}else if(result == "failed"){
				alert("操作超时!");
			}else if(result == "only"){
				$("#add_formtip").css("color","green");
				$("#add_formtip").html("<li>该商品编号可以正常使用。</li>");
				$("#add_formtip").attr("key","0");
			}
		},'html');
	}
});

/**
 * 修改商品时：验证该商品编码是否存在
 * */
$("#m_goodsSN").blur(function(){
	//去掉两端空白部分
	var agsn = $.trim($("#m_goodsSN").val());
	//输入不为空
	if(agsn != ""){
		//验证商品编码是否存在的ajax
		$.post(path+"/backend/goodsSNisexit.shtml",{'goodsSN':agsn,'id':$("#m_id").val()},function(result){
			if(result == "repeat"){
				$("#modify_formtip").css("color","red");
				$("#modify_formtip").html("<li>对不起，该商品编号已存在。</li>");
				$("#modify_formtip").attr("key","1");
				result = false;
			}else if(result == "failed"){
				alert("操作超时!");
			}else if(result == "only"){
				$("#modify_formtip").css("color","green");
				$("#modify_formtip").html("<li>该商品编号可以正常使用。</li>");
				$("#modify_formtip").attr("key","0");
			}
		},'html');
	}
});

/**
 * 添加商品弹窗的“保存”按钮被点击进的验证方法
 * */
function addGoodsInfoFunction(){
	$("#add_formtip").html("");
	var result = true;
	if($.trim($("#a_goodsName").val()) == "" || $("#a_goodsName").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，商品名称不能为空。</li>");
		result = false;
	}
	if($.trim($("#a_goodsSN").val()) == "" || $("#a_goodsSN").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，商品编号不能为空。</li>");
		result = false;
	}else{
		if($("#add_formtip").attr("key") == "1"){
			$("#add_formtip").append("<li>对不起，该商品编号已存在。</li>");
			result = false;
		}
	}
	if($.trim($("#a_marketPrice").val()) == "" || $("#a_marketPrice").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，市场价不能为空。</li>");
		result = false;
	}
	if($.trim($("#a_realPrice").val()) == "" || $("#a_realPrice").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，优惠价不能为空。</li>");
		result = false;
	}
	if($.trim($("#a_num").val()) == "" || $("#a_num").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，库存量不能为空。</li>");
		result = false;
	}
	if($.trim($("#a_unit").val()) == "" || $("#a_unit").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，单位不能为空。</li>");
		result = false;
	}
	
	return result;
}

/**
 * 修改商品弹窗的“保存”按钮被点击进的验证方法
 * */
function modifyGoodsInfoFunction(){
	$("#modify_formtip").html("");
	var result = true;
	if($.trim($("#m_goodsName").val()) == "" || $("#m_goodsName").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，商品名称不能为空。</li>");
		result = false;
	}
	if($.trim($("#m_goodsSN").val()) == "" || $("#m_goodsSN").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，商品编号不能为空。</li>");
		result = false;
	}else{
		if($("#modify_formtip").attr("key") == "1"){
			$("#modify_formtip").append("<li>对不起，该商品编号已存在。</li>");
			result = false;
		}
	}
	if($.trim($("#m_marketPrice").val()) == "" || $("#m_marketPrice").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，市场价不能为空。</li>");
		result = false;
	}
	if($.trim($("#m_realPrice").val()) == "" || $("#m_realPrice").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，优惠价不能为空。</li>");
		result = false;
	}
	if($.trim($("#m_num").val()) == "" || $("#m_num").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，库存量不能为空。</li>");
		result = false;
	}
	if($.trim($("#m_unit").val()) == "" || $("#m_unit").val() == null){
		$("#modify_formtip").css("color","red");
		$("#modify_formtip").append("<li>对不起，单位不能为空。</li>");
		result = false;
	}
	
	return result;
}

/**
 * 点击状态（checked=true时，state=1）"直接勾选修改状态，立即生效"
 * */
$(".modifystate").click(function(){
	//获取对象
	var modify = $(this);
	//获取该对象的属性值 
	var state= modify.attr("state");
	var id= modify.attr("goodsinfoid");
	//创建新对象
	goodsInfoStart = new Object();
	goodsInfoStart.id = id;
	//下架
	if(state == "1"){
		goodsInfoStart.state = 2;
		modify.attr("state","2");
	}
	else{   //上架
		goodsInfoStart.state = 1;
		modify.attr("state","1");
	}
	//点击勾选后的修改状态的ajax
	$.ajax({
		url: path+'/backend/modifystate.shtml',
		type: 'POST',
		data:{state:JSON.stringify(goodsInfoStart)},//json对象
		dataType: 'html',
		timeout: 1000,
		error: function(){
			alert("上架或下架商品操作时失败！请重试。");
		},
		success: function(result){
			if(result != "" && "success" == result){
				if(state == "1")
					modify.attr("state",2);
				else
					modify.attr("state",1);
			}else if("failed" == result){
				alert("上架或下架商品操作时失败！请重试。");
			}else if("nodata" == result){
				alert("对不起，没有任何数据需要处理！请重试。");
			}
		}
	});
});

/**
 * 提示函数
 * */
function showmsg(msg){
	var divBox = document.getElementById("showmsgBox") || "";
	if(divBox){
		divBox.style.display = "block";
	}else{
		divBox = document.createElement("span");
		divBox.className = "showmsgBox"; 
		divBox.setAttribute("id","showmsgBox");  
		document.body.appendChild(divBox);
	}
	divBox.innerHTML = msg; 
	divBox.style.left = document.documentElement.clientWidth/2 + "px"
	setTimeout(function(){ 
		 divBox.style.display = "none";
	},1000);
}

