package com.sl.pojo;

import java.util.Date;

//用户资金提现记录
public class UserCash extends Base {
  private String cash_id; //id
  private Date cash_time; //申请提现时间
  private String cash_num; //提现单号
  private String user_id; //提现人
  private Double cash_PV; //提现PV
  private String currency; //提现货币
  private Double cash_money; //提现金额
  private String note; //提现备注
  private Double fee; //手续费
  private Double tax; //税费
  private Double other_fee; //其他费用
  private String bank_name; //提现银行名
  private String bank_brance; //银行开户行名
  private String bank_account; //银行账号
  private String account_name; //账号姓名
  private int stat; //0申请提现,1审核通过,2转账完毕
  private Double credited_money; //到账金额
  private Date credited_time; //到账时间
  private String operator; //操作人
public String getCash_id() {
	return cash_id;
}
public void setCash_id(String cash_id) {
	this.cash_id = cash_id;
}
public Date getCash_time() {
	return cash_time;
}
public void setCash_time(Date cash_time) {
	this.cash_time = cash_time;
}
public String getCash_num() {
	return cash_num;
}
public void setCash_num(String cash_num) {
	this.cash_num = cash_num;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public Double getCash_PV() {
	return cash_PV;
}
public void setCash_PV(Double cash_PV) {
	this.cash_PV = cash_PV;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public Double getCash_money() {
	return cash_money;
}
public void setCash_money(Double cash_money) {
	this.cash_money = cash_money;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Double getFee() {
	return fee;
}
public void setFee(Double fee) {
	this.fee = fee;
}
public Double getTax() {
	return tax;
}
public void setTax(Double tax) {
	this.tax = tax;
}
public Double getOther_fee() {
	return other_fee;
}
public void setOther_fee(Double other_fee) {
	this.other_fee = other_fee;
}
public String getBank_name() {
	return bank_name;
}
public void setBank_name(String bank_name) {
	this.bank_name = bank_name;
}
public String getBank_brance() {
	return bank_brance;
}
public void setBank_brance(String bank_brance) {
	this.bank_brance = bank_brance;
}
public String getBank_account() {
	return bank_account;
}
public void setBank_account(String bank_account) {
	this.bank_account = bank_account;
}
public String getAccount_name() {
	return account_name;
}
public void setAccount_name(String account_name) {
	this.account_name = account_name;
}
public int getStat() {
	return stat;
}
public void setStat(int stat) {
	this.stat = stat;
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
public String getOperator() {
	return operator;
}
public void setOperator(String operator) {
	this.operator = operator;
}

}
