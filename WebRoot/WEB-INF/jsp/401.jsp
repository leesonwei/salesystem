<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <meta charset="utf-8">
    <meta content="width=device-width,initial-scale=1.0" name="viewport">
    <meta content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template." name="description">
    <meta content="Muhammad Usman" name="author">
    <title>401未授权页面</title>
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap-cerulean.css" rel="stylesheet">   
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/charisma-app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/fullcalendar.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/fullcalendar.print.css" rel="stylesheet"  media="print">
	<link href="${pageContext.request.contextPath}/statics/css/chosen.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/uniform.default.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/colorbox.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/jquery.cleditor.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/jquery.noty.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/noty_theme_default.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/elfinder.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/elfinder.theme.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/jquery.iphone.toggle.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/opa-icons.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/css/uploadify.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/img/favicon.ico" rel="shortcut icon">
	<link href="${pageContext.request.contextPath}/statics/localcss/common.css" rel="stylesheet">
  </head>
  
  <body>
  
      <div class="container-fluid">
           <div class="row-fluid">
                <div class="row-fluid">
                    <div class="span12 center login-header">
					     <h2>对不起，您没有权限访问，请返回到<a href="${pageContext.request.contextPath}/main.html">首页</a></h2>
				    </div>
                </div>
                <div class="row-fluid">
                     <div class="well span5 center login-box">
					      <img src="${pageContext.request.contextPath}/statics/img/jg.png"/>
				     </div>
                </div>
           </div>
      </div>
      
      <%@ include file="/WEB-INF/jsp/common/js.jsp" %>
  </body>
</html>
