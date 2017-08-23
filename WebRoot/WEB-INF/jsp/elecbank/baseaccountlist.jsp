<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<!-- 导航面包屑 -->
<div>
	<ul class="breadcrumb">
		<li>
		    <a href="#">电子银行</a> <span class="divider">/</span>
		</li>
		<li>
		    <a href="${pageContext.request.contextPath}/elecbank/baseAccount.html">基本账户</a>
		</li>
	</ul>
</div>

<!-- 可分类的 -->
<div class="row-fluid sortable">
	<div class="box span12">
		<!-- 箱子头部开始 -->
		<div class="box-header well" data-original-title>
			<h2>
				<i class="icon-user"></i> 基本账户日志
			</h2>			
		</div>
		<!-- 箱子头部结束 -->

		<!-- 箱子内容开始 -->
		<div class="box-content">
			<!-- 关于查询的表单start -->
			<form action="${pageContext.request.contextPath}/elecbank/baseAccount.html" method="post">
				<div class="searcharea">
				               <span> 时间：</span>
                                <input type="text" class="Wdate"  readonly="readonly" onclick="WdatePicker()" 
                                name="queryStartTime" id="queryStartTime" value="${queryStartTime }"/>
                                  <input class="Wdate" readonly="readonly" onclick="WdatePicker()" type="text"
                                   name="queryEndTime" id="queryEndTime" value="${queryEndTime }"/>
							<button type="submit" class="btn btn-primary">
								<i class="icon-search icon-white"></i> 查询
							</button>
				   </div>
			</form>
			<!-- 关于查询的表单end -->
            
            <!-- 表格显示的内容start -->
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
				<thead>
					<tr>
						<th>日期</th>
						<th>时间</th>
						<th>摘要</th>
						<th>入账金额（元）</th>
						<th>出账金额（元）</th>
						<th>余额（元）</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${page.items != null}">
						<c:forEach items="${page.items}" var="baseaccount">
							<tr>
								<td class="center"><fmt:formatDate value="${baseaccount.actionTime}" pattern="yyyy-MM-dd" /></td>
								<td class="center"><fmt:formatDate value="${baseaccount.actionTime}" pattern="HH-mm-ss" /></td>
								<td class="center">${baseaccount.actionDesc}</td>
								<td class="center">${baseaccount.baseIn}</td>
								<td class="center">${baseaccount.baseOut}</td>
								<td class="center">${baseaccount.baseBalance}</td>																							
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<!-- 表格显示的内容end -->
			
			<!-- 分页start -->
			<div class="pagination pagination-centered">
				<ul>
					<c:choose>
						<c:when test="${page.page == 1}">
							<li class="active"><a href="javascript:void();" title="首页">首页</a></li>
						</c:when>
						<c:otherwise>
							<li>
							    <a href="${pageContext.request.contextPath}/elecbank/baseAccount.html?currentpage=1&queryStartTime=${queryStartTime }&queryEndTime=${queryEndTime }"
								title="首页">首页</a>
							</li>
						</c:otherwise>
					</c:choose>
					<c:if test="${page.prevPages!=null}">
						<c:forEach items="${page.prevPages}" var="num">
							<li>
							   <a href="${pageContext.request.contextPath}/elecbank/baseAccount.html?currentpage=${num}&queryStartTime=${queryStartTime }&queryEndTime=${queryEndTime }"
								class="number" title="${num}">${num}</a>
							</li>
						</c:forEach>
					</c:if>
					<li class="active"><a href="#" title="${page.page}">${page.page}</a></li>
					<c:if test="${page.nextPages!=null}">
						<c:forEach items="${page.nextPages}" var="num">
							<li>
							   <a href="${pageContext.request.contextPath}/elecbank/baseAccount.html?currentpage=${num}&queryStartTime=${queryStartTime }&queryEndTime=${queryEndTime }"
								title="${num}"> ${num} </a>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${page.pageCount !=null}">
						<c:choose>
							<c:when test="${page.page == page.pageCount}">
								<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
							</c:when>
							<c:otherwise>
								<li>
								   <a href="${pageContext.request.contextPath}/elecbank/baseAccount.html?currentpage=${page.pageCount}&queryStartTime=${queryStartTime }&queryEndTime=${queryEndTime }"
									title="尾页">尾页</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${page.pageCount == null}">
						<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
					</c:if>
				</ul>
			</div>
			<!-- 分页end -->
		</div>
		<!-- 结束.box-content -->
		<!-- 箱子内容结束 -->
	</div>
	<!--结束.box .span12-->
</div>
<!--结束.row-fluid .sortable-->

<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<!-- <script type="text/javascript">
    var cartTypeListJson =	[<c:forEach  items="${cardTypeList}" var="cardType"> 
							{"valueId":"${cardType.valueId}","valueName":"${cardType.valueName}"},
							</c:forEach>{"valueId":"over","valueName":"over"}];
    var roleListJson =	[<c:forEach  items="${roleList}" var="role"> 
						{"id":"${role.id}","roleName":"${role.roleName}"},
						</c:forEach>{"id":"over","roleName":"over"}];
</script> -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/localjs/baseaccountlist.js"></script>
