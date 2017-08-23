<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>电子银行>>内部转账</h2>					
					</div>
					<div class="box-content">
						<form id="formid" class="form-horizontal" action="" method="post">
						<input type="hidden" id="sessionpassword2" value="${userSession.password2 }">
						<input type="hidden" id="base_balance" value="${userAccount.base_balance}">
						<input type="hidden" id="user_id" value="${userAccount.user_id}">
						  <fieldset>							
							 <div class="control-group">
								<label class="control-label" for="focusedInput">当前余额：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="focusedInput" readonly="readonly"  type="text" value="${userAccount.base_balance}(元)">
								</div>
							  </div>
							
							 <div class="control-group">
								<label class="control-label" for="focusedInput">转账目标会员：</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="loginCode" id="loginCode"  required="required" type="text" >
								   <span ></span>
								</div>
							  </div>
						 <div class="control-group">
								<label class="control-label" for="focusedInput">转账金额</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="transferMoney" id="transferMoney" type="text" onkeyup="value=value.replace(/[^\d{0,8}\.{0,1}(\d{1,2})?$]/g,'')" required="required" >
								  <span></span>
								</div>
							  </div>
						
							 <div class="control-group">
								<label class="control-label" for="focusedInput">二级密码</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="password2" id="password2"  required="required" type="text" >
								  <span></span>
								</div>
							  </div> 
							<div class="form-actions">
							  <button type="button" id="btnSubm" class="btn btn-primary">提交</button>
							</div>
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->

    	<div class="modal hide fade" id="transfer">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>确认转账信息！</h3>
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
<script src="${pageContext.request.contextPath }/statics/localjs/innertransger.js"></script>
