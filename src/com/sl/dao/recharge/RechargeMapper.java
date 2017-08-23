package com.sl.dao.recharge;

import java.util.List;

import com.sl.pojo.UserRecharge;



public interface RechargeMapper {
	public List<UserRecharge> getRechargeList();
	public boolean addRecharge(UserRecharge userRecharge);
}
