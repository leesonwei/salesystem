<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- <%@include file="/WEB-INF/jsp/common/head.jsp"%> --%>
<%@include file="common/head.jsp"%>
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
                    <table style="width:100%;">
                         <c:if test="${afficheList != null}">
                               <c:forEach items="${afficheList}" var="affiche">
                                   <tr>
                                       <td style="width:20px;padding:3px;"><span class="icon icon-color icon-info"></span></td>
                                       <td><a href="${pageContext.request.contextPath}/informanage/portalAfficheDetail?id=${affiche.id}">${affiche.title}</a></td>
                                       <td style="width:80px;"><fmt:formatDate value="${affiche.publishTime}" pattern="yyyy-MM-dd" /></td>
                                   </tr>
                               </c:forEach>
                         </c:if>                   
                    </table>
               </div>
          </div>
     </div>
     <!-- 公告栏end -->
     
     <!-- 资讯栏start -->
     <div class="row-fluid sortable">
          <div class="box span12">
               <!-- 标题头 -->
               <div class="box-header well" data-original-title>
                    <h2><i class="icon-file"></i>资讯栏</h2>
                    <div calss="box-icon">
                         <a href="${pageContext.request.contextPath}/informanage/portalinfoList">more >></a>
                    </div>
               </div>
               <!-- 相关内容 -->
               <div class="box-content">
                    <table style="width:100%;">
                         <c:if test="${infoList != null}">
                               <c:forEach items="${infoList}" var="info">
                                   <tr>
                                       <td style="width:20px;padding:3px;"><span class="icon icon-color icon-info"></span></td>
                                       <td><a href="${pageContext.request.contextPath}/informanage/portalInfoDetail?id=${info.id}">${info.title}</a></td>
                                       <td style="width:80px;"><fmt:formatDate value="${info.publishTime}" pattern="yyyy-MM-dd" /></td>
                                   </tr>
                               </c:forEach>
                         </c:if>                   
                    </table>
               </div>
          </div>
     </div>
     <!-- 资讯栏end -->








<%-- <%@include file="/WEB-INF/jsp/common/foot.jsp"%> --%>
<%@include file="common/foot.jsp"%>