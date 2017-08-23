<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>电子银行>>汇款充值>>汇款单</h2>					
					</div>
					<div class="box-content">
					<h3>选择在线支付银行</h3>
						<form id="formid" class="form-horizontal" action="${pageContext.request.contextPath }/elecbank/rechargesave" method="post">
						        <input type="hidden" id="path" value="${pageContext.request.contextPath }" >
					            <div class="">
					                 <input type="hidden"  name="recharge_time" value="<fmt:formatDate value='${userRecharge.recharge_time}' pattern='yyyy-MM-dd' />" />
					                 <input type="hidden"  name="recharge_money" value="${userRecharge.recharge_money}" />
					                 <input type="hidden"  name="note" value="${userRecharge.note}" />
					                 <input type="hidden"  name="recharge_num" value="${userRecharge.recharge_num}" />
								      <input type="radio" width="80px"  name="bank_name" value="中国银行" />中国银行
								      <input type="radio" width="80px" name="bank_name" value="工商银行" />工商银行
								      <input type="radio" width="80px" name="bank_name" value="信用卡	" />信用卡								  
								      <span id="rad"></span>  
                                 </div>
                                 <div class="infoTip" style="color:red">
                                    <label ><i class="icon-hand-right"></i>提示：</label> 
                                    <span>点击图标进入在线支付系统，在线支付可能需要花费一些时间,请您在提交个人卡信息后，
                                                                                                         一定要等看到我们的系统显示支付返回结果充值成功提示后再关闭支付页面，否则您所支
                                                                                                         付的款将不能自动充值到您的“基本帐户”。</span>
                                 </div>
                                 <div class="form-actions">
							    <button type="button" id="btnSave" class="btn btn-primary">提交</button>
							    </div>
						</form>   
					</div>
				</div><!--/span-->
   
			</div><!--/row-->

    	<div class="modal hide fade" id="RechargeSave">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>确认汇款信息！</h3>
			</div>
			<div class="modal-body">
				<p>Here settings can be configured...</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">取消</a>
				<a href="javascript:subSave();" class="btn btn-primary">提交</a>
			</div>
		</div>


<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script src="${pageContext.request.contextPath }/statics/localjs/selectBank.js"></script>
