package com.sl.service.recharge;

import java.util.List;

import com.sl.pojo.UserRecharge;



public interface RechargeService {
	public List<UserRecharge> getRechargeList();//获取充值记录
	public boolean addRecharge(UserRecharge userRecharge);//添加充值记录
}
