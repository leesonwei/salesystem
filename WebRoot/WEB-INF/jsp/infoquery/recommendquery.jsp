<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<link href="${pageContext.request.contextPath}/statics/localcss/recommendquery.css" rel="stylesheet">
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
							 <ul>
							  <li>							    
							     <span><i class="glyphicon glyphicon-folder-open"></i>${user.loginCode }</span> <a href="javascript:viewUser(${user.id})">${user.loginCode }</a>							    							  							  
							     <ul>
							       <c:forEach items="${referList }" var="refer">
							         <li>
							             <span><i class="glyphicon glyphicon-leaf"></i>${refer.loginCode }</span><a href="javascript:viewUser(${refer.id})">${refer.loginCode }</a>
							        </li>
							       </c:forEach>
							     </ul>
							  </li>							  
							 </ul>
						</div>
						<div class="" style="float:right;width:500px;">
						     <div ><i class="icon-info-sign"></i><Strong>会员信息</Strong></div>
						      <div class="viewInfo"></div> 
						</div>						
					</div>
				</div><!--/span-->

			</div><!--/row-->

    
	
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script src="${pageContext.request.contextPath }/statics/localjs/recommendquery.js"></script>