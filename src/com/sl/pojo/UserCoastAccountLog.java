package com.sl.pojo;

import java.util.Date;

public class UserCoastAccountLog extends Base {
    private int accountLogId;  //总账明细表Id
    private int userId ;  //用户ID
    private Date actionTime ;  //记录日期
    private String actionDesc;  // 动作摘要
    private int actionType;  //  0辅消,1重消,2,入账  
    private Double repeatIn;  // 重消入账
    private Double repeatOut ;  //重消出账,
    private Double repeatBalance;  // 重消余额
	public int getAccountLogId() {
		return accountLogId;
	}
	public void setAccountLogId(int accountLogId) {
		this.accountLogId = accountLogId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	public Double getRepeatIn() {
		return repeatIn;
	}
	public void setRepeatIn(Double repeatIn) {
		this.repeatIn = repeatIn;
	}
	public Double getRepeatOut() {
		return repeatOut;
	}
	public void setRepeatOut(Double repeatOut) {
		this.repeatOut = repeatOut;
	}
	public Double getRepeatBalance() {
		return repeatBalance;
	}
	public void setRepeatBalance(Double repeatBalance) {
		this.repeatBalance = repeatBalance;
	}
}
