<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>电子银行>>汇款充值</h2>					
					</div>
					<div class="box-content">
						<form id="formid" class="form-horizontal" action="selectbank" method="post">
						  <fieldset>							
							<div class="control-group">
							  <label class="control-label" for="date01">汇款日期:</label>
							  <div class="controls">
								<input type="text" name="recharge_time"  class="Wdate"  readonly="readonly" onclick="WdatePicker()" id="date01" >
								<span></span>
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="recharge_money">汇款金额：</label>
							  <div class="controls">
								<input class="input-file uniform_on" name="recharge_money" id="recharge_money" type="text" onkeyup="value=value.replace(/[^\d{0,8}\.{0,1}(\d{1,2})?$]/g,'')" required="required">
								<span>（CNY）</span>
								<span></span>
							  </div>
							</div>          
							<div class="control-group">
							  <label class="control-label" for="textarea2">摘要：</label>
							  <div class="controls">
								<textarea class="cleditor" name="note" id="textarea2" rows="3"></textarea>								
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="recharge_num">单号：</label>
							  <div class="controls">
								<input class="input-file uniform_on" name="recharge_num"  id="recharge_num" type="text">
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

    	<div class="modal hide fade" id="addRecharge">
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
<script src="${pageContext.request.contextPath }/statics/localjs/recharge.js"></script>
