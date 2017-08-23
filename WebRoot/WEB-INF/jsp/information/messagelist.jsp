<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- <%@include file="/WEB-INF/jsp/common/head.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
     <!-- 留言管理start -->
<div id="myTabs">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><h4>留言列表</h4></a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
	<!-- 已回复留言列表 -->
	<div role="tabpanel" class="tab-pane active" id="home">
		<c:forEach items="${leaveMessages}" var="message">
		<div class="panel panel-success" style="box-shadow: 0px 0px 4px #ccc;border: solid 1px #CCC;margin-bottom:15px; padding:10px;" id="${message.id}"> 
			<div class="panel-body">
			<h4>${message.createdBy}&nbsp;留言: <span class="pull-right deleteMessage">删除</span><span class="pull-right reply">回复&nbsp;&nbsp;&nbsp;</span></h4> 
			${message.messageContent} <span class="pull-right">${message.createTime }</span></div>
			<c:forEach items="${replyList}" var="reply">
			<c:if test="${reply.messageId ==message.id }">
				<div class="panel-body" style="border-top: solid 1px #CCC;margin:10px 0px 0px 0px;padding:10px 0px 0px 20px;"><h4>${reply.createdBy}&nbsp;回复:</h4> ${reply.replyContent} <span class="pull-right">${reply.createTime }</span></div> 
			</c:if>
			</c:forEach>
		</div>
		</c:forEach>
	</div><!-- 已回复留言列表 -->
  </div>

</div>
     <!-- 留言管理end -->
 
 
<%-- <%@include file="/WEB-INF/jsp/common/foot.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>

<script src="${pageContext.request.contextPath}/statics/localjs/information/message.js"></script>
