package com.sl.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;


//用户充值记录
public class UserRecharge {
	  private String recharge_id;//recharge_id
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	  //@JSONField(format="yyyy-MM-dd")
	  private Date recharge_time;//充值时间
	  private String recharge_num;//充值单号
	  private String user_id ;// 
	  private String currency ;//充值货币
	  private Double recharge_money ;//充值金额
	  private String note ;//摘要备注
	  private Double credited_money;//到账金额
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	  //@JSONField(format="yyyy-MM-dd")
	  private Date credited_time  ;//到账时间
	  private String audit_user ;//审核人
	  private Double PV_rate ;//PV比率
	  private Double PV ;//兑换后PV值
	  private String bank_name;//充值银行名
	  private String bank_account ;//充值账号名
	  private String platform  ;//充值平台
	  private String param ;//充值接口参数
	public String getRecharge_id() {
		return recharge_id;
	}
	public void setRecharge_id(String recharge_id) {
		this.recharge_id = recharge_id;
	}
	public Date getRecharge_time() {
		return recharge_time;
	}
	public void setRecharge_time(Date recharge_time) {
		this.recharge_time = recharge_time;
	}
	public String getRecharge_num() {
		return recharge_num;
	}
	public void setRecharge_num(String recharge_num) {
		this.recharge_num = recharge_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getRecharge_money() {
		return recharge_money;
	}
	public void setRecharge_money(Double recharge_money) {
		this.recharge_money = recharge_money;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Double getCredited_money() {
		return credited_money;
	}
	public void setCredited_money(Double credited_money) {
		this.credited_money = credited_money;
	}
	public Date getCredited_time() {
		return credited_time;
	}
	public void setCredited_time(Date credited_time) {
		this.credited_time = credited_time;
	}
	public String getAudit_user() {
		return audit_user;
	}
	public void setAudit_user(String audit_user) {
		this.audit_user = audit_user;
	}
	
	public Double getPV() {
		return PV;
	}
	public void setPV(Double pV) {
		PV = pV;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public Double getPV_rate() {
		return PV_rate;
	}
	public void setPV_rate(Double pV_rate) {
		PV_rate = pV_rate;
	}
}
