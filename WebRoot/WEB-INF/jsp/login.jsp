<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>登录页面</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/login/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/login/css/camera.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/login/matrix-login.css" />
<link href="${pageContext.request.contextPath}/statics/login/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.5.1.min.js"></script>

</head>
<body>

	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm"
				id="loginForm">
				<div class="control-group normal_text">					
					<h2>SL会员商城</h2>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"><i><img height="37"
									src="${pageContext.request.contextPath}/statics/login/user.png" /></i></span><input type="text"
								name="loginCode" id="loginCode" value=""
								placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly"><i><img height="37"
									src="${pageContext.request.contextPath}/statics/login/suo.png" /></i></span><input type="password"
								name="password" id="password" placeholder="请输入密码"
								value="" />
						</div>
					</div>
				</div>
				<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						
						<span class="pull-right" style="padding-right:3%;"><a
							href="javascript:quxiao();" class="btn btn-success">取消</a></span> <span
							class="pull-right"><a onclick="severCheck();"
							class="flip-link btn btn-info" id="to-recover">登录</a></span>

					</div>
				</div>

			</form>


			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © Kivy
							2100</span></font>
				</div>
			</div>
		</div>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<div data-src="${pageContext.request.contextPath}/statics/login/images/banner_slide_01.jpg"></div>
			<div data-src="${pageContext.request.contextPath}/statics/login/images/banner_slide_02.jpg"></div>
			<div data-src="${pageContext.request.contextPath}/statics/login/images/banner_slide_03.jpg"></div>
		</div>
		<!-- #camera_wrap_3 -->
	</div>

	<script type="text/javascript">
	
		//服务器校验
		function severCheck(){
			if(check()){
				
				var loginCode = $("#loginCode").val();
				var password = $("#password").val();			
				$.ajax({
					type: "POST",
					url: "login.html",
			    	data: {loginCode:loginCode,password:password},
					dataType:"html",
					//cache: false,
					success: function(data){
						if("success" == data){
							saveCookie();
							window.location.href="main.html";
						}else if("failed" == data){
							$("#loginCode").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
							$("#loginCode").focus();
						}else{
							$("#loginCode").tips({
								side : 1,
								msg : "缺少参数",
								bg : '#FF5080',
								time : 15
							});
							$("#loginCode").focus();
						}
					}
				});
			}
		}
			
		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});
		
		//客户端校验
		function check() {

			if ($("#loginCode").val() == "") {

				$("#loginCode").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginCode").focus();
				return false;
			} else {
				$("#loginCode").val(jQuery.trim($('#loginCode').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			}
			
			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginCode', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginCode").val('');
				$("#password").val('');
			}
		}

        //保存cookie的方法
		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginCode', $("#loginCode").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		//取消的方法 
		function quxiao() {
			$("#loginCode").val('');
			$("#password").val('');
		}
		
		jQuery(function() {
			var loginCode = $.cookie('loginCode');
			var password = $.cookie('password');
			if (typeof(loginCode) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginCode").val(loginCode);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
	</script>
	<!-- <script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script> -->

	<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/statics/js/jquery-1.7.2.js"></script>
	<script src="${pageContext.request.contextPath}/statics/login/js/jquery.easing.1.3.js"></script>
	<script src="${pageContext.request.contextPath}/statics/login/js/jquery.mobile.customized.min.js"></script>
	<script src="${pageContext.request.contextPath}/statics/login/js/camera.min.js"></script>
	<script src="${pageContext.request.contextPath}/statics/login/js/templatemo_script.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.tips.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.cookie.js"></script>
</body>

</html>