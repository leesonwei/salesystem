<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>    
    <meta charset="utf-8">
    <meta content="width=device-width,initial-scale=1.0" name="viewport">
    <meta content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template." name="description">
    <meta content="Muhammad Usman" name="author">
    <title>SL会员商城系统</title>
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
	
	<!-- 在文件夹localcss中加入自定义的样式 -->
	<link href="${pageContext.request.contextPath}/statics/localcss/common.css" rel="stylesheet">
	
	<script type="text/javascript"> var tt='${mList}';</script> 
  </head>
  
  <body>
     
      <!-- 顶部条start --> 
      <div class="navbar">
           <div class="navbar-inner">
                 <div class="container-fluid">
                      <input type="hidden" id="path" value="${pageContext.request.contextPath}" >
                      <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                      </a>
                      
                      <a href="${pageContext.request.contextPath}/main.html" class="brand">
                           <img alt="Logo" src="${pageContext.request.contextPath}/statics/img/logo20.png" />
                           <span>SL会员商城</span>
                      </a>
                      
                      <div class="btn-group pull-right">
                           <ul class="nav">
                               <li><a href="#">您好，${user.loginCode}</a></li>
                               <li><a href="#">角色：${user.roleName}</a></li>
                               <li><a href="${pageContext.request.contextPath}/main.html">首页</a></li>
                               <!-- <li><a href="#">购物车</a></li> -->
                               <li><a href="#">留言板</a></li>
                               <li><a href="javascript:void();" class="btn-setting modifypwd">修改密码</a></li>
                               <li><a href="${pageContext.request.contextPath}/logout">注销</a></li>
                           </ul>
                      </div>
                      
                      <div id="myModal" class="modal hide fade" >
                           <div class="modal-header">
                                <button class="close" type="button" data-dismiss="modal">×</button>
                                <h3>修改密码</h3>
                           </div>
                           <div class="modal-body">
                                <p>
                                    <label>请输入原密码：</label>
                                    <input type="password" id="oldpassword" /><span>*</span>
                                    <label>请输入新密码：</label>
                                    <input type="password" id="newpassword" /><span>*</span> 
                                    <label>请再次输入新密码：</label>
                                    <input type="password" id="aginpassword" /><span>*</span>                                     
                                </p>
                                <p id="modifypwdtip"></p>
                           </div>
                           <div class="modal-footer">
                                <a href="#" class="btn" data-dismiss="modal">取消</a>
                                <a class="btn btn-primary" href="#" id="modifySavePassword">修改</a>
                           </div>
                      </div>
                      
                 </div><!-- 结束.container-fluid -->
           </div><!-- 结束.navbar-inner -->
      </div> <!-- 结束.navbar -->
      <!-- 顶部条end --> 
      
      <!-- 流体内容start -->
      <div class="container-fluid">
           
           <!-- 省略了头部部分 -->
           
           <!-- 行式流体start -->
           <div class="row-fluid">
                <!-- 左侧菜单 -->
                <div class="span2 main-menu-span">
                     <div class="well nav-collapse sidebar-nav">
                         <ul id="menus" class="nav nav-tabs nav-stacked main-menu"></ul>
                     </div>
                </div>
                
                <!-- 定义在脚本未被执行时的替代内容 -->
                <noscript>
                     <div class="alert alert-block span10">
                          <h3 class="alert-heading">警告!</h3>
                          <p>操作须谨慎！可点击<a href="#" target="_blank">这里</a>进行了解！</p>
                     </div>
                </noscript>
                
                <!-- 以下是我们需要添加的页面内容区域 -->
                <div class="span10" id="content">
  
                <%--  <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath}" /> --%>
            <%-- <input type="hidden" id="referer" name="referer" value="<%= request.getHeader("Referer") %>" /> --%>
