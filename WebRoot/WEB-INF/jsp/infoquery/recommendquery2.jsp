<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<%-- <link href="${pageContext.request.contextPath}/statics/localcss/recommendquery.css" rel="stylesheet"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/zTree_v3-master/css/demo.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>


<div class="row-fluid sortable">
				<div class="box span12">
				    <input type="hidden" id="userid" value="${user.id }">
				     <input type="hidden" id="path" value="${pageContext.request.contextPath}">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>信息查询>推荐查询</h2>					
					</div>
					<div class="box-content">
				      <div class="tree well"  style="float:left;width:300px;border:1px solid red;">
				          <div><i class="icon-user"></i><Strong>推荐会员</Strong></div>
							 <ul id="treeDemo" class="ztree"></ul>
						</div>
						<div class="" style="float:right;width:500px;">
						     <div ><i class="icon-info-sign"></i><Strong>会员信息</Strong></div>
						      <div class="viewInfo"></div> 
						</div>						
					</div>
				</div><!--/span-->

			</div><!--/row-->

    
	
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<%-- <script src="${pageContext.request.contextPath }/statics/localjs/recommendquery.js"></script> --%>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/zTree_v3-master/js/jquery.ztree.core.js"></script>
  <SCRIPT LANGUAGE="JavaScript">
   var zTreeObj;
   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   var setting = {};
   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
   var zNodes = [
   {name:"test1", open:true, children:[
      {name:"test1_1"}, {name:"test1_2"}]},
   {name:"test2", open:true, children:[
      {name:"test2_1"}, {name:"test2_2"}]}
   ];

      zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);

  </SCRIPT>