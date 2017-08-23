package com.sl.service.usercash;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.dao.usercash.UserCashMapper;
import com.sl.pojo.UserCash;
@Service
public class UserCashServiceImpl implements UserCashService {
   @Autowired
   @Resource
   private UserCashMapper userCashMapper;
	@Override
	public int getUserCashCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userCashMapper.getUserCashCount(map);
	}

	@Override
	public List<UserCash> getUserCashList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userCashMapper.getUserCashList(map);
	}

	@Override
	public boolean add(UserCash userCash) {
		try {
			userCashMapper.add(userCash);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
