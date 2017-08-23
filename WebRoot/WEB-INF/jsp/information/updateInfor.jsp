<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- <%@include file="/WEB-INF/jsp/common/head.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

     <!-- 资讯栏start -->
     <div class="row-fluid sortable">
          <div class="box span12">
               <!-- 标题头 -->
               <div class="box-header well" data-original-title>
                    <h2><i class="icon-th"></i>资讯栏</h2>
                    <div calss="box-icon">
                         <a href="${pageContext.request.contextPath}/informanage/portalafficheList">more >></a>
                    </div>
               </div>
               <!-- 相关内容 -->
               <div class="box-content">
                    	<form action="${pageContext.request.contextPath}" id="addInforForm">
		
		<div class="modal-header">
			<button type="button" class="close addusercancel" data-dismiss="modal">×</button>
			<h3>修改资讯</h3>
		</div>
		
		<div class="modal-body">
			<ul id="add_formtip"></ul><!-- 在此处提示信息 -->
			<ul class="topul">
				<li>
				    <label>ID：</label>
				    <input type="text" id="inforId" name="inforId" value="${information.id}"/><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>标题：</label>
				    <input type="text" id="title" name="title" value="${information.title}"/><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>内容：</label>
				    <textarea type="text" id="title2" name="title2">${information.content}</textarea> <span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>状态：</label> 
				    <select name="state" style="width:100px;" id="state">
						<option value="" selected="selected">--请选择--</option>
						<c:choose>
							<c:when test="${information.state==1 }">
								<option value="1" selected>已发布</option>
								<option value="2">待发布</option>
								<option value="3">已过期</option>
							</c:when>
							<c:when test="${information.state==2 }">
								<option value="1">已发布</option>
								<option value="2" selected>待发布</option>
								<option value="3">已过期</option>
							</c:when>
							<c:otherwise>
								<option value="1">已发布</option>
								<option value="2">待发布</option>
								<option value="3" selected>已过期</option>
							</c:otherwise>
						</c:choose>
				    </select>
				</li>
				<li>
				    <label>类型：</label>
				    <input type="text" id="typename" name="typename" value="${information.typename}"/> <span style="color:red;font-weight: bold;">*</span>
				</li>
			</ul>
			<!-- 清除浮动 -->
			<div class="clear"></div>
			<ul class="downul">
				<li>
				    <label>上传资讯：</label> <input type="text" id="filepath" name="filepath"  value="${information.filepath}"/> 
				    <input id="inputInforFile" name="inputInforFile" type="file" /> 
				    <input type="button" id="uploadInfor" value="上传" />
					<p>
						<span style="color:red;font-weight: bold;">*注：上传文件大小不能超过50M</span>
					</p>
					<input type="hidden" id="filename" name="filename" value="" />
					<input type="hidden" id="filesize" name="filesize" value="" />
					<input type="hidden" id="uploadtime" name="uploadtime" value="" />
					<div id="result"></div>
				</li>
			</ul>
		</div>

		<div class="modal-footer">
			<a href="#" class="btn addusercancel">取消</a> 
			<input type="button" class="btn btn-primary updateInfor" value="保存" />
		</div>
	</form>
               </div>
          </div>
     </div>
     <!-- 资讯栏end -->


<%-- <%@include file="/WEB-INF/jsp/common/foot.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>

<script src="${pageContext.request.contextPath}/statics/localjs/information/information.js"></script>