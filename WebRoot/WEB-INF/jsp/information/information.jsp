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
                    <table style="width:100%;" id="inforTable" style="text-align:left;">
                    	<tr><th></th><th style="text-align:left;">文件标题</th><th style="text-align:left;">下载说明</th><th style="text-align:left;">文件大小M</th><th style="text-align:left;">状态</th><th style="text-align:left;">最后修改时间</th><th style="text-align:left;">操作</th></tr>
                         <c:if test="${informationList != null}">
                               <c:forEach items="${informationList}" var="information">
                                   <tr>
                                       <td><span class="icon icon-color icon-info"></span></td>
                                       <td><a class="viewInfor" inforid="${information.id}"  src="${information.filepath}">${information.title}</a></td>
                                       <td><a href="/salesystem/informanage/downloadcenter.html" src="${information.filepath}">${information.content}</a></td>
                                       <td>${information.filesize==0?"无":information.filesize}</td>
                                       <td><c:if test="${information.state==1}"><input type="checkbox" name="state" checked/></c:if>
                                       		<c:if test="${information.state!=1}"><input type="checkbox" name="state"/></c:if></td>
                                       <td><fmt:formatDate value="${information.publishtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                       <td><a href="${pageContext.request.contextPath}/informanage/updateInfor.html?id=${information.id}" class="viewInfor" infor=""><span class="glyphicon glyphicon-open" style="color: rgb(27, 167, 60);font-size:14px;">修改</span></a>&nbsp;
                                       		<a class="deleteInfor" inforId="${information.id}"><span class="glyphicon glyphicon-remove" style="color: rgb(213, 66, 60);font-size:14px;">删除</span></a>
                                       </td>
                                   </tr>
                               </c:forEach>
                         </c:if>                   
                    </table>
               </div>
          </div>
             <div calss="pull-right">
                  <button class="btn btn-primary add">添加</button>
             </div>
     </div>
     <!-- 资讯栏end -->
     
<!-- 弹窗模式：添加资讯start -->
<div class="modal hide fade" id="addInforDiv">
	<form action="${pageContext.request.contextPath}" id="addInforForm">
		
		<div class="modal-header">
			<button type="button" class="close addusercancel" data-dismiss="modal">×</button>
			<h3>添加资讯</h3>
		</div>
		
		<div class="modal-body">
			<ul id="add_formtip"></ul><!-- 在此处提示信息 -->
			<ul class="topul">
				<li>
				    <label>标题：</label>
				    <input type="text" id="title" name="title"/><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>内容：</label>
				    <textarea type="text" id="title2" name="title2"> </textarea> <span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>状态：</label> 
				    <select name="state" style="width:100px;" id="state">
						<option value="" selected="selected">--请选择--</option>
						<option value="1">已发布</option>
						<option value="2">待发布</option>
						<option value="3">已过期</option>
				    </select>
				</li>
				<li>
				    <label>类型：</label>
				    <input type="text" id="typename" name="typename"/> <span style="color:red;font-weight: bold;">*</span>
				</li>
			</ul>
			<!-- 清除浮动 -->
			<div class="clear"></div>
			<ul class="downul">
				<li>
				    <label>上传资讯：</label> <input type="hidden" id="filepath" name="filepath" value="" /> 
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
			<a href="#" class="btn addusercancel" data-dismiss="modal">取消</a> 
			<input type="button" class="btn btn-primary saveInfor" value="保存" />
		</div>
	</form>
</div>
<!-- 弹窗模式：添加资讯end -->
<!-- 弹窗模式：查看资讯start -->
<div class="modal hide fade" id="viewInforDiv">
	<form action="${pageContext.request.contextPath}" id="addInforForm">
		
		<div class="modal-header">
			<button type="button" class="close addusercancel" data-dismiss="modal">×</button>
			<h3>查看资讯</h3>
		</div>
		
		<div class="modal-body">
			
		</div>

		<div class="modal-footer">
			<a href="#" class="btn addusercancel" data-dismiss="modal">取消</a> 
			<input type="button" class="btn btn-primary saveInfor" value="点击下载" />
		</div>
	</form>
</div>
<!-- 弹窗模式：查看资讯end -->

<%-- <%@include file="/WEB-INF/jsp/common/foot.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>

<script src="${pageContext.request.contextPath}/statics/localjs/information/information.js"></script>