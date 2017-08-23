package com.sl.pojo;

import java.sql.Date;
//用户基本账户和消费账户信息
public class UserAccount {
   private int account_id;  //ID
   private int user_id;  //用户ID
   private Date account_date;  //日期
   private int stat;  //总账状态
   private Double base_in;  //基本入账
   private Double base_out;  //基本出账
   private Double base_balance;  //基本余额
   private Double repeat_in;  //重消入账
   private Double repeat_out;  //重消出账
   private Double repeat_balance;  //重消余额
   private int free_PV;  //未分红重消PV
   private int already_PV;  //已分红未领货重消PV
   private int buy_PV;  //已领货重消PV
public int getAccount_id() {
	return account_id;
}
public void setAccount_id(int account_id) {
	this.account_id = account_id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public Date getAccount_date() {
	return account_date;
}
public void setAccount_date(Date account_date) {
	this.account_date = account_date;
}
public int getStat() {
	return stat;
}
public void setStat(int stat) {
	this.stat = stat;
}
public Double getBase_in() {
	return base_in;
}
public void setBase_in(Double base_in) {
	this.base_in = base_in;
}
public Double getBase_out() {
	return base_out;
}
public void setBase_out(Double base_out) {
	this.base_out = base_out;
}
public Double getBase_balance() {
	return base_balance;
}
public void setBase_balance(Double base_balance) {
	this.base_balance = base_balance;
}
public Double getRepeat_in() {
	return repeat_in;
}
public void setRepeat_in(Double repeat_in) {
	this.repeat_in = repeat_in;
}
public Double getRepeat_out() {
	return repeat_out;
}
public void setRepeat_out(Double repeat_out) {
	this.repeat_out = repeat_out;
}
public Double getRepeat_balance() {
	return repeat_balance;
}
public void setRepeat_balance(Double repeat_balance) {
	this.repeat_balance = repeat_balance;
}
public int getFree_PV() {
	return free_PV;
}
public void setFree_PV(int free_PV) {
	this.free_PV = free_PV;
}
public int getAlready_PV() {
	return already_PV;
}
public void setAlready_PV(int already_PV) {
	this.already_PV = already_PV;
}
public int getBuy_PV() {
	return buy_PV;
}
public void setBuy_PV(int buy_PV) {
	this.buy_PV = buy_PV;
}
}
