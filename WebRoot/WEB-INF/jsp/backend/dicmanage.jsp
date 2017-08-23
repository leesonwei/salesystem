<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div>
	<ul class="breadcrumb">
		<li><a href="#">后台管理</a> <span class="divider">/</span></li>
		<li><a href="${pageContext.request.contextPath}/backend/dicmanage.html">数据字典管理</a></li>
	</ul>
</div>


<div class="row-fluid sortable">		
	<div class="box span12">
	    
	    <!-- 标题 -->
		<div class="box-header well" data-original-title>
			<h2><i class="icon-user"></i> 数据字典管理 </h2>
		</div>
		
		<!-- 内容 -->
		<div class="box-content">
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
				  <tbody>
				     <tr>
						  <td width="160px;">
								  <ul class="dllist">
									  <li><h3>字典类型</h3></li>
									  <li class="addDicBtn"><img src="${pageContext.request.contextPath}/statics/img/ico7.png"/></li>
									  <c:forEach items="${dataList}" var="dl">
										  	<li class="maintitle">							  	
										  		<a class="typecodelist" typename="${dl.typeName}" typecode="${dl.typeCode}" dlid="${dl.id}">
										  		     ${dl.typeName}
										  		</a>
										  		<span class="mainset">
													<img class="modifyMainDic" dictypename="${dl.typeName}" dicid="${dl.id}" dictypecode="${dl.typeCode}" src="${pageContext.request.contextPath}/statics/img/ico10.png"> 
													<img class="delMainDic" dictypecode="${dl.typeCode}" dictypename="${dl.typeName}" src="${pageContext.request.contextPath}/statics/img/linkdel.png">
												</span>
										  	</li>
									  </c:forEach>
									  <!--  点击“新增字典”按钮 -->
									  <li class="addDicBtn"><img src="${pageContext.request.contextPath}/statics/img/ico7.png"/></li>
								  </ul>
						  </td>
						  <td>	
						       <!-- 显示相应的标题信息 -->					  
							   <h3 id="optitle"></h3>
							   <div class="dicListContent">
								  	<ul id="dicListUL"></ul>
								  	<ul id="addsubdicul">
								  	    <!--  点击“新增子字典”按钮 -->
								  		<li id="addDicLiBtn" class="addDicLiBtn"><img src="${pageContext.request.contextPath}/statics/img/winapp_add.png"/></li>
							  	    </ul>
							  </div>					  
						  </td>
				      </tr>
				  </tbody>
		  </table>   
	   </div>
	   
     </div>
     <!--结束.box .span12-->
</div>
<!--结束.row-fluid .sortable-->

<!-- 新增字典弹窗start -->
<div class="modal hide fade" id="addDicModel">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>添加数据字典</h3>
		</div>
		
		<div class="modal-body">
			<p>
				<label>类型代码：</label>
				<input id="typeCode"  type="text" />
				<label>类型名称：</label>
				<input id="typeName"  type="text" />					 
		    </p>
		    <!-- 结果信息提示 -->
		    <p id="addDictip"></p>
	    </div>
	    
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">取消</a>
			<a href="#" id="addDicExeBtn" class="btn btn-primary">添加</a>
		</div>
</div>
<!-- 新增字典弹窗end -->		

<!-- 新增字典子菜单弹窗start -->			
<div class="modal hide fade" id="addDicSubModel">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>添加数据字典</h3>
		</div>
		
		<div class="modal-body">
			<p>
				<label>类型代码：</label>
				<input id="typeDicSubCode" disabled="disabled"  type="text" />
				<label>类型名称：</label>
				<input id="typeDicSubName" disabled="disabled"  type="text" />
				<label>数据名称：</label>
				<input id="valueDicSubName"  type="text" />
			</p>
			<!-- 结果信息提示 -->
			<p id="addDicSubtip"></p>
		</div>
		
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">取消</a>
			<a href="#" id="addDicsubExeBtn" class="btn btn-primary">添加</a>
		</div>
</div>
<!-- 新增字典子菜单弹窗end -->
	
<!-- 修改字典弹窗start -->		
<div class="modal hide fade" id="modifyDicModel">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>修改数据字典</h3>
		</div>
		
		<div class="modal-body">
			<p>
				<label>类型代码：</label>
				<input id="modifytypeCode"  type="text" />
				<label>类型名称：</label>
				<input id="modifytypeName"  type="text" />
				<input id="modifydicid"  type="hidden" />
				<input id="modifydictypecode"  type="hidden" />
				<input id="modifydictypename"  type="hidden" />
			</p>
			<!-- 结果信息提示 -->
			<p id="modifyDictip"></p>
		</div>
		
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">取消</a>
			<a href="#" id="modifyDicExeBtn" class="btn btn-primary">修改</a>
		</div>
</div>
<!-- 修改字典弹窗end -->


<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/localjs/dicmanage.js"></script>