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
                    <table style="width:100%;" id="afficheTable">
                         <c:if test="${afficheList != null}">
                               <c:forEach items="${afficheList}" var="affiche">
                                   <tr>
                                       <td style="width:20px;padding:3px;"><span class="icon icon-color icon-info"></span></td>
                                       <td><a href="${pageContext.request.contextPath}/informanage/portalAfficheDetail?id=${affiche.id}">${affiche.title}</a></td>
                                       <td>${affiche.publisher}</td>
                                       <td><fmt:formatDate value="${affiche.publishtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                       <td><a href="${pageContext.request.contextPath}/informanage/updateAffiche.html?id=${affiche.id}" class="viewAffiche" infor=""><span class="glyphicon glyphicon-open" style="color: rgb(27, 167, 60);font-size:14px;">修改</span></a>&nbsp;
                                       		<a class="deleteAffiche" afficheId="${affiche.id}"><span class="glyphicon glyphicon-remove" style="color: rgb(213, 66, 60);font-size:14px;">删除</span></a>
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
     <!-- 公告栏end -->
<!-- 弹窗模式：添加用户start -->
<div class="modal hide fade" id="addAfficheDiv">
	<form action="${pageContext.request.contextPath}" id="addAfficheForm">
		
		<div class="modal-header">
			<button type="button" class="close addusercancel" data-dismiss="modal">×</button>
			<h3>添加公告</h3>
		</div>
		
		<div class="modal-body">
			<ul id="add_formtip"></ul><!-- 在此处提示信息 -->
			<ul class="topul">
				<li>
				    <label>标题：</label>
				    <input type="text" id="title" name="title"/><span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>开始时间：</label>
				    <input type="text" id="startTime" name="startTime" onclick="WdatePicker()"/> <span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>结束时间：</label>
				    <input type="text" id="endTime" name="endTime"  onclick="WdatePicker()"/> <span style="color:red;font-weight: bold;">*</span>
				</li>
				<li>
				    <label>内容：</label>
				    <textarea id="title2" name="title2"> </textarea> <span style="color:red;font-weight: bold;">*</span>
				</li>
			</ul>
			<!-- 清除浮动 -->
			<div class="clear"></div>
		</div>

		<div class="modal-footer">
			<a href="#" class="btn addusercancel" data-dismiss="modal">取消</a> 
			<input type="button" class="btn btn-primary saveAffiche" value="保存" />
		</div>
	</form>
</div>
<!-- 弹窗模式：添加用户end -->     








<%-- <%@include file="/WEB-INF/jsp/common/foot.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script src="${pageContext.request.contextPath}/statics/localjs/information/affiche.js"></script>