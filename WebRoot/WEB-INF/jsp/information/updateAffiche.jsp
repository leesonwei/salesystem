<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- <%@include file="/WEB-INF/jsp/common/head.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

     <!-- 公告栏start -->
     <div class="row-fluid sortable">
          <div class="box span12">
               <!-- 标题头 -->
               <div class="box-header well" data-original-title>
                    <h2><i class="icon-th"></i>公告栏</h2>
                    <div calss="box-icon">
                         <a href="${pageContext.request.contextPath}/informanage/portalafficheList">more >></a>
                    </div>
               </div>
               <!-- 相关内容 -->
               <div class="box-content">
                    	<form action="${pageContext.request.contextPath}" id="updateAfficheForm">
		
		<div class="modal-header">
			<h3>修改公告</h3>
		</div>
		
		<div class="modal-body">
			<ul id="add_formtip"></ul><!-- 在此处提示信息 -->
			<ul class="topul">
				<li>
				    <label>ID：</label>
				    <input type="text" id="afficheId" name="id" value="${affiche.id }" disabled/><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>标题：</label>
				    <input type="text" id="title" name="title" value="${affiche.title }"/><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>开始时间：</label>
				    <input type="text" id="startTime" name="startTime" onclick="WdatePicker()" value="${affiche.startTime}"/> <span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>结束时间：</label>
				    <input type="text" id="endTime" name="endTime"  onclick="WdatePicker()" value="${affiche.endTime}"/> <span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>内容：</label>
				    <textarea id="title2" name="title2">${affiche.content }</textarea> <span style="color:red;font-weight: bold;">*</span>
				</li>
			</ul>
			<!-- 清除浮动 -->
			<div class="clear"></div>
		</div>

		<div class="modal-footer">
			<a href="#" class="btn addusercancel">取消</a> 
			<input type="button" class="btn btn-primary updateAffiche" value="保存" />
		</div>
	</form>
               </div>
          </div>
     </div>
     <!-- 资讯栏end -->


<%-- <%@include file="/WEB-INF/jsp/common/foot.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>

<script src="${pageContext.request.contextPath}/statics/localjs/information/affiche.js"></script>