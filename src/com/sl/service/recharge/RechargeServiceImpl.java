package com.sl.service.recharge;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.dao.recharge.RechargeMapper;
import com.sl.pojo.UserRecharge;


@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    @Resource
    private RechargeMapper rechargeMapper;
	@Override
	public List<UserRecharge> getRechargeList() {
		// TODO Auto-generated method stub
		return rechargeMapper.getRechargeList();
	}

	@Override
	public boolean addRecharge(UserRecharge userRecharge) {
		// TODO Auto-generated method stub
		return rechargeMapper.addRecharge(userRecharge);
	}

}
