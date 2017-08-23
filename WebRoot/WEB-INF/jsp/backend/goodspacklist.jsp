<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<!-- 标题start -->
<div>
	<ul class="breadcrumb">
		<li><a href="#">后台管理</a> <span class="divider">/</span></li>
		<li><a href="${pageContext.request.contextPath}/backend/goodspacklist.html">商品套餐管理</a></li>
	</ul>
</div>
<!-- 标题end -->

<!-- 内容start -->
<div class="row-fluid sortable">
	<div class="box span12">
	    <!-- 标题栏 -->
		<div class="box-header well" data-original-title>
			<h2>
				<i class="icon-user"></i> 商品套餐列表
			</h2>
			<!-- 添加商品套餐 -->
			<div class="box-icon">
				<span class="icon32 icon-color icon-add custom-setting addGoodsPack" />
			</div>
		</div>

        <!-- 内容栏start -->
		<div class="box-content">
		    <!-- 搜索表单start -->
			<form action="${pageContext.request.contextPath}/backend/goodspacklist.html" method="post">
				<div class="searcharea">
					商品套餐名称: <input type="text" name="s_goodsPackName" value="${s_goodsPackName}" /> 
					套餐类型： <select name="s_typeId" style="width:100px;">
						            <option value="" selected="selected">--请选择--</option>
									<c:if test="${packTypeList != null}">
										<c:forEach items="${packTypeList}" var="packType">
											<option <c:if test="${s_typeId == packType.id}">selected = "selected"</c:if> value="${packType.valueId}">
											       ${packType.valueName}
											</option>
										</c:forEach>
									</c:if>
					       </select> 
				            状态：    <select name="s_state" style="width:100px;">
									<option value="" selected="selected">--请选择--</option>
									<c:if test="${s_state == 1}">
										<option value="1" selected="selected">上架</option>
										<option value="2">下架</option>
									</c:if>
									<c:if test="${s_state == 2}">										
										<option value="1">上架</option>
										<option value="2" selected="selected">下架</option>
									</c:if>
									<c:if test="${s_state == null || s_state == ''}">										
										<option value="1">上架</option>
										<option value="2">下架</option>
									</c:if>
					       </select>
					<button type="submit" class="btn btn-primary">
						<i class="icon-search icon-white"></i> 查询
					</button>
				</div>
			</form>
		   <!-- 搜索表单end -->
           
           <!-- 表格内容start -->
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
				<thead>
					<tr>
						<th>套餐编号</th>
						<th>套餐名称</th>
						<th>套餐总价(元)</th>
						<th>库存量</th>
						<th>状态(上架/下架)</th>
						<th>套餐类型</th>
						<th>最后更新时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${page.items != null}">
						<c:forEach items="${page.items}" var="goodsPack">
							<tr>
								<td class="center">${goodsPack.goodsPackCode}</td>
								<td class="center">${goodsPack.goodsPackName}</td>
								<td class="center">${goodsPack.totalPrice}</td>
								<td class="center">${goodsPack.num}</td>
								<td class="center">
								    <input type="checkbox"
									title="直接勾选修改状态，立即生效" data-rel="tooltip" class="modifystate"
									state="${goodsPack.state}" goodspackid="${goodsPack.id}"
									<c:if test="${goodsPack.state == 1}"> checked="true"</c:if> />
								</td>
								<td class="center">${goodsPack.typeName}</td>
								<td class="center">
								    <fmt:formatDate value="${goodsPack.lastUpdateTime}" pattern="yyyy-MM-dd" /></td>
								<td class="center">
								    <a class="btn btn-success viewgoodspack" href="#" id="${goodsPack.id}"><i class="icon-zoom-in icon-white"></i> 查看 </a> 
								    <a class="btn btn-info modifygoodspack" href="#" id="${goodsPack.id}"><i class="icon-edit icon-white"></i>修改 </a> 
								    <a class="btn btn-danger delgoodspack" href="#" id="${goodsPack.id}" goodsPackName="${goodsPack.goodsPackName}"><i class="icon-trash icon-white"></i> 删除 </a>
							    </td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<!-- 表格内容end -->
			
			<!-- 分页start -->
			<div class="pagination pagination-centered">
				<ul>
				    <!-- 首页 -->
					<c:choose>
						<c:when test="${page.page == 1}">
							<li class="active"><a href="javascript:void();" title="首页">首页</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/backend/goodspacklist.html?currentpage=1&s_goodsPackName=${s_goodsPackName}&s_state=${s_state}&s_typeId=${s_typeId}" title="首页">首页</a></li>
						</c:otherwise>
					</c:choose>
					
					<!-- 上一页 -->
					<c:if test="${page.prevPages!=null}">
						<c:forEach items="${page.prevPages}" var="num">
							<li><a href="${pageContext.request.contextPath}/backend/goodspacklist.html?currentpage=${num}&s_goodsPackName=${s_goodsPackName}&s_state=${s_state}&s_typeId=${s_typeId}" class="number" title="${num}">${num}</a></li>
						</c:forEach>
					</c:if>
					
					<!-- 页码 -->
					<li class="active"><a href="#" title="${page.page}">${page.page}</a></li>
					
					<!-- 下一页 -->
					<c:if test="${page.nextPages!=null}">
						<c:forEach items="${page.nextPages}" var="num">
							<li><a href="${pageContext.request.contextPath}/backend/goodspacklist.html?currentpage=${num}&s_goodsPackName=${s_goodsPackName}&s_state=${s_state}&s_typeId=${s_typeId}" title="${num}"> ${num}</a></li>
						</c:forEach>
					</c:if>
					
					<!-- 尾页 -->
					<c:if test="${page.pageCount !=null}">
						<c:choose>
							<c:when test="${page.page == page.pageCount}">
								<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/backend/goodspacklist.html?currentpage=${page.pageCount}&s_goodsPackName=${s_goodsPackName}&s_state=${s_state}&s_typeId=${s_typeId}" title="尾页">尾页</a></li>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${page.pageCount == null}">
						<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
					</c:if>
				</ul>
			</div>
			<!-- 分页end -->
		</div>
		<!-- 内容栏end -->
		
	</div>
	<!--结束.box .span12-->
</div>
<!--结束.row-fluid .sortable-->
<!-- 内容end -->

<!-- 添加商品套餐弹窗start -->
<%-- <div class="modal hide fade" id="addGoodsPackDiv">
	<form action="${pageContext.request.contextPath}/backend/saveaddgoodspack.html" enctype="multipart/form-data"
		method="post" onsubmit="return addGoodsPackFunc();">
		
		<div class="modal-header">
			<button type="button" class="close addgoodspackcancel" data-dismiss="modal">×</button>
			<h3>添加商品套餐信息</h3>
		</div>
		
		<div class="modal-body">
		    <!-- 添加商品的提示信息 -->
			<ul id="add_formtip"></ul>
			<ul class="topul">
				<li>
				   <label>套餐名称：</label>
				   <input type="text" id="a_goodsPackName" name="goodsPackName" /><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				   <label>套餐编号：</label>
				   <input type="text" id="a_goodsPackCode" name="goodsPackCode" /><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				   <label>套餐类型：</label> 
				   <input type="hidden" id="select_a_typeName" name="typeName" value="" />
				   <select id="select_a_typeId" name="typeId" style="width:100px;">
			 			<option value="" selected="selected">--请选择--</option>
			 			<c:if test="${packTypeList != null}">
			 				<c:forEach items="${packTypeList}" var="packType">
			 					<option value="${packType.valueId}">${packType.valueName}</option>
			 				</c:forEach>
			 			</c:if>
			 		</select>
					<span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				   <label>库存量：</label> 
				   <input type="text" id="a_num" name="num" 
				      onkeyup="this.value=this.value.replace(/\D/g,'')" 
				      onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
				   <span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				   <label>套餐总价：</label>
				   <input type="text" id="a_totalPrice" name="totalPrice" value="0" 
				      onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;"/> 
				   <span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				   <label>状态：</label> 
				   <input type="radio" id="a_stateup" name="state" checked="checked" value="1"/>上架
				   <input type="radio" id="a_statedown" name="state" value="0"/>下架
				</li>
				<li>
				   <label>相关商品：</label> 
				   <ul class="aboutproductsList">
						<li><iframe id="goodsListFrame" class="goodsListFrame" src="${pageContext.request.contextPath}/backend/goodsinfolist.html"></iframe></li>
						<li id="selectgoodslist"></li>
					</ul>
					<input id="goodsJson" type="hidden" name="goodsJson"/>
				</li>
				<li>
				   <label>套餐说明：</label> 
				   <textarea class="cleditor" id="a_note" name="note" rows="3"></textarea>
				</li>
			</ul>			
		</div>
		
		<div class="modal-footer">
			<a href="#" class="btn addgoodspackcancel" data-dismiss="modal">取消</a>
			<input type="submit" class="btn btn-primary" value="保存" />
		</div>
		
	</form>
</div> --%>
<!-- 添加商品套餐弹窗end -->

<!-- 修改商品套餐弹窗start -->
<%-- <div class="modal hide fade" id="modifyGoodsPackDiv">
	<form action="${pageContext.request.contextPath}/backend/savemodifygoodspack.shtml"
		enctype="multipart/form-data" method="post"
		onsubmit="return modifyGoodsPackFunc();">
		
		<div class="modal-header">
			<button type="button" class="close modifygoodspackcancel" data-dismiss="modal">×</button>
			<h3>修改商品信息</h3>
		</div>
		
		<div class="modal-body">
			<ul id="modify_formtip"></ul>
			<input id="m_id" type="hidden" name="id" />
			<ul class="topul">
				<li>
				    <label>套餐名称：</label>
				    <input type="text" id="m_goodsPackName" name="goodsPackName" /><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>套餐编号：</label>
				    <input type="text" id="m_goodsPackCode" name="goodsPackCode" /><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>套餐类型：</label>				    			 			
			 		</select>
			 		<input type="hidden" id="m_typeName" name="typeName" value="" />
				   <select id="m_typeId" name="typeId" style="width:100px;">
			 			<option value="" selected="selected">--请选择--</option>
			 			<c:if test="${packTypeList != null}">
			 				<c:forEach items="${packTypeList}" var="packType">
			 					<option value="${packType.valueId}">${packType.valueName}</option>
			 				</c:forEach>
			 			</c:if>
			 		</select>
					<span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>库存量：</label> 
				    <input type="text" id="m_num" name="num" value="" 
				         onkeyup="this.value=this.value.replace(/\D/g,'')" 
				         onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
					<span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>套餐总价：</label>
				    <input type="text" id="m_totalPrice" name="totalPrice" value="" 
				        onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;"/>
					<span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>状态：</label> 
				    <c:if test="${goodsPack.state == '1'}">
					 	<input type="radio" name="state" checked="checked" value="1"/>上架
					  	<input type="radio" name="state" value="0"/>下架
					</c:if>
					<c:if test="${goodsPack.state == '0'}">
					 	<input type="radio" name="state" value="1"/>上架
					  	<input type="radio" name="state" checked="checked" value="0"/>下架
					</c:if>
				</li>
				<li>
				    <label>相关商品：</label>
				    <ul class="aboutproductsList">
						  	<li><iframe id="goodsListFrame" class="goodsListFrame" src="${pageContext.request.contextPath}/backend/goodsinfolist.html"></iframe></li>
						  	<li id="selectgoodslist">
						  		<c:if test="${goodsList != null}">
					 				<c:forEach items="${goodsList}" var="goods">
						 				<div id="selectdiv">
							 				<label class="goodsname">${goods.goodsName}</label>
											<label class="goodscount"><input class="finalresult" goodsid="${goods.goodsInfoId}" rprice="${goods.realPrice}" type="text" value="${goods.goodsNum}"/></label>
											<label class="del" rprice="${goods.realPrice}"><img src="${pageContext.request.contextPath}/statics/img/cancel-on.png"/></label>
											<label class="clear"></label>
										</div>
					 				</c:forEach>
					 	    	</c:if>
						  	</li>
					 </ul>
					 <input id="goodsJson" type="hidden" name="goodsJson"/>
				</li>
				<li>
				    <label>套餐说明：</label>
				    <textarea class="cleditor" id="m_note" name="note" rows="3"></textarea>
				</li>
			</ul>			
		</div>

		<div class="modal-footer">
			<a href="#" class="btn modifygoodspackcancel" data-dismiss="modal">取消</a>
			<input type="submit" class="btn btn-primary" value="保存" />
		</div>
		
	</form>
</div> --%>
<!-- 修改商品套餐弹窗end -->

<!-- 查看商品套餐弹窗start -->
<div class="modal hide fade" id="viewGoodsPackDiv">

	<div class="modal-header">
		<button type="button" class="close viewgoodspackcancel" data-dismiss="modal">×</button>
		<h3>查看商品信息</h3>
	</div>
	
	<div class="modal-body">
		<ul class="topul">
			<li>
			    <label>套餐名称：</label>
			    <input type="text" id="v_goodsPackName" name="goodsPackName" readonly="readonly" />
			</li>
			<li>
			    <label>套餐编号：</label>
			    <input type="text" id="v_goodsPackCode" name="goodsPackCode" readonly="readonly" />
			</li>
			<li>
			    <label>套餐类型：</label>
			    <input type="text" id="v_typeName" name="typeName" readonly="readonly" />
		    </li>
			<li>
			    <label>库存量：</label> 
			    <input type="text" id="v_num" name="num" readonly="readonly" />
			</li>
			<li>
			    <label>套餐总价：</label>
			    <input type="text" id="v_totalPrice" name="totalPrice" readonly="readonly" />
		    </li>	
			<li>
			    <label>状态：</label> <span id="v_state"></span>
			</li>
			<li>
			    <label>相关商品：</label> 
			    <c:if test="${goodsList != null}">
					  <c:forEach items="${goodsList}" var="goods">
						<ul>
							${goods.goodsName}&nbsp;&nbsp;&nbsp;&nbsp;
							${goods.goodsNum}&nbsp;
							${goods.unit}
						</ul>
					  </c:forEach>
				</c:if>
				<c:if test="${goodsList == null}">
					 暂无
				</c:if>
			</li>
			<li>
			    <label>套餐说明：</label> 
			    <input type="text" id="v_note" name="note" readonly="readonly" />
			</li>
		</ul>		
	</div>
	
	<div class="modal-footer">
		<a href="#" class="btn viewgoodspackcancel" data-dismiss="modal">关闭</a>
	</div>
	
</div>
<!-- 查看商品套餐弹窗end -->

<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/localjs/goodspacklist.js"></script>
