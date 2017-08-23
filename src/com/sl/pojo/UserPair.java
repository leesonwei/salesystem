package com.sl.pojo;

import java.util.Date;

//用户对碰记录数
public class UserPair {
	private String pair_id;// pair_id
	private Date date  ;//  前一天的日期
	private String bonus_user_id  ;// 
	private Double left_user  ;// 左用户数
	private Double right_user ;//右用户数
	private Double left_keep ;//左保留总数
	private Double right_keep;// 右保留总数
	private Double each_PV;// 从数据字典获取，目前是1000
	private Double sum_pair_PV;// 总碰PV
	private Double bouns_rate ;// 奖金比率
	private Double bouns_PV;//对碰奖励PV
	public String getPair_id() {
		return pair_id;
	}
	public void setPair_id(String pair_id) {
		this.pair_id = pair_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBonus_user_id() {
		return bonus_user_id;
	}
	public void setBonus_user_id(String bonus_user_id) {
		this.bonus_user_id = bonus_user_id;
	}
	public Double getLeft_user() {
		return left_user;
	}
	public void setLeft_user(Double left_user) {
		this.left_user = left_user;
	}
	public Double getRight_user() {
		return right_user;
	}
	public void setRight_user(Double right_user) {
		this.right_user = right_user;
	}
	public Double getLeft_keep() {
		return left_keep;
	}
	public void setLeft_keep(Double left_keep) {
		this.left_keep = left_keep;
	}
	public Double getRight_keep() {
		return right_keep;
	}
	public void setRight_keep(Double right_keep) {
		this.right_keep = right_keep;
	}
	
	public Double getSum_pair_PV() {
		return sum_pair_PV;
	}
	public void setSum_pair_PV(Double sum_pair_PV) {
		this.sum_pair_PV = sum_pair_PV;
	}
	public Double getBouns_rate() {
		return bouns_rate;
	}
	public void setBouns_rate(Double bouns_rate) {
		this.bouns_rate = bouns_rate;
	}
	public Double getBouns_PV() {
		return bouns_PV;
	}
	public void setBouns_PV(Double bouns_PV) {
		this.bouns_PV = bouns_PV;
	}
	public Double getEach_PV() {
		return each_PV;
	}
	public void setEach_PV(Double each_PV) {
		this.each_PV = each_PV;
	}
}
