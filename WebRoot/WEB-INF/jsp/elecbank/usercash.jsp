<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="row-fluid sortable">
          
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>电子银行>>申请提现</h2>					
					</div>
					<div class="box-content">
						<form id="formid" class="form-horizontal" action="usrcashsave" method="post">
						  <fieldset>							
							 <div class="control-group">
								<label class="control-label" for="focusedInput">当前余额：</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="base_balance" readonly="readonly"  type="text" value="${userAccount.base_balance}(元)">
								</div>
							  </div>
							<div class="control-group">
							  <label class="control-label" for="cash_money">提现金额：</label>
							  <div class="controls">
								<input class="input-file uniform_on" name="cash_money" id="cash_money" type="text" onkeyup="value=value.replace(/[^\d{0,8}\.{0,1}(\d{1,2})?$]/g,'')" required="required">
								<span></span>
							  </div>
							</div> 														         
							<div class="control-group">
							  <label class="control-label" for="textarea2">银行所在地址及支行详细名称：</label>
							  <div class="controls">
								<textarea class="cleditor" name="bank_brance" id="bank_brance" rows="3"></textarea>	
								<span></span>							
							  </div>
							</div>
							
							 <div class="control-group">
								<label class="control-label" for="bank_name">银行名称</label>
								<div class="controls">
								  <select id="bank_name" name="bank_name">
									<option value="">--请选择--</option>
									<option value="中国银行">中国银行</option>
									<option value="北京银行">北京银行</option>
									<option value="中国农业银行">中国农业银行</option>
									<option value="中国建设银行">中国建设银行</option>
									<option value="中国工商银行">中国工商银行</option>
									<option value="中国邮政">中国邮政</option>
								  </select>
								  <span></span>
								</div>
							  </div>
							
							<div class="control-group">
							  <label class="control-label" for="recharge_money">用户名称：</label>
							  <div class="controls">
								<input class="input-file uniform_on" name="account_name" id="account_name" type="text"  required="required">
								<span></span>
							  </div>
							</div> 
								<div class="control-group">
							  <label class="control-label" for="recharge_money">银行卡号：</label>
							  <div class="controls">
								<input class="input-file uniform_on" name="bank_account" id="bank_account" type="text"  required="required">
								<span></span>
							  </div>
							</div> 
							
							<div class="form-actions">
							  <button type="button" id="btnSubm" class="btn btn-primary">提交申请</button>
							</div>
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->

    	<div class="modal hide fade" id="cashtip">		
			<div class="modal-body">
				<p><i class=" icon-hand-right"></i>  您好，根据公司的提现规定，每月10号之前申请提现，30号发放，
				20号之前申请提现，次月10号发放，每月30号之前申请提现，次月20号发放，
				提现有5%手续费（国际汇款费用，汇率差）。 谢谢!</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">好的，继续</a>
			</div>
		</div>
		
		<div class="modal hide fade" id="cashinfo">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>确认提现信息！</h3>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<a href="javascript:" class="btn" data-dismiss="modal">取消</a>
				<a href="javascript:subSave();" class="btn btn-primary">提交</a>
			</div>
		</div>
	
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script src="${pageContext.request.contextPath }/statics/localjs/usercash.js"></script>
