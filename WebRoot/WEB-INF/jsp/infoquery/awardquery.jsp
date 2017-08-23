<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<!-- 导航面包屑 -->
<div>
	<ul class="breadcrumb">
		<li>
		    <a href="#">信息查询</a> <span class="divider">/</span>
		</li>
		<li>
		    <a href="${pageContext.request.contextPath}/elecbank/applyMoneyInfo.html">奖励查询</a>
		</li>
	</ul>
</div>

<!-- 可分类的 -->
<div class="row-fluid sortable">
	<div class="box span12">
		<!-- 箱子头部开始 -->
		<div class="box-header well" data-original-title>
			<h2 color="red">
				<i class="icon-user"></i>个人资料
			</h2>			
		</div>
		<!-- 箱子头部结束 -->
          
		<!-- 箱子内容开始 -->
		<div class="box-content">
		   <table class="table table-striped table-bordered bootstrap-datatable datatable">						  
			  <tbody>
				<tr>
		         <td>级别</td>
		         <td>${user.userTypeName }</td>
		         <td>本人直推会员</td>
		         <td>${num }</td>
		      </tr>
		      </tbody>
		   </table>
		</div>
		<div class="box-content">
		
		</div>
		<!-- 结束.box-content -->
		<!-- 箱子内容结束 -->
	</div>
	<!--结束.box .span12-->
</div>
<!--结束.row-fluid .sortable-->

<%@include file="/WEB-INF/jsp/common/foot.jsp"%>


