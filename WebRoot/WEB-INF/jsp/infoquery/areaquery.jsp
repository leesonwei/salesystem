<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<!-- 导航面包屑 -->
<div>
	<ul class="breadcrumb">
		<li>
		    <a href="#">信息查询</a> <span class="divider">/</span>
		</li>
		<li>
		    <a href="${pageContext.request.contextPath}/elecbank/applyMoneyInfo.html">区域查询</a>
		</li>
	</ul>
</div>

<!-- 可分类的 -->
<div class="row-fluid sortable">
	<div class="box span12">
		<!-- 箱子头部开始 -->
		<div class="box-header well" data-original-title>
			<h2 color="red">
				<i class="icon-flag"></i>点击图中任意图标或者用户名则从该节点开始显示关系图
			</h2>			
		</div>
		<!-- 箱子头部结束 -->

		<!-- 箱子内容开始 -->
		<div class="box-content">
		
		</div>
		<!-- 结束.box-content -->
		<!-- 箱子内容结束 -->
	</div>
	<!--结束.box .span12-->
</div>
<!--结束.row-fluid .sortable-->

<%@include file="/WEB-INF/jsp/common/foot.jsp"%>


