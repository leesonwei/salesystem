package com.sl.service.useraccount;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.dao.useraccount.UserAccountMapper;
import com.sl.pojo.UserAccount;
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    @Resource
    private UserAccountMapper userAccountMapper;
	@Override
	public UserAccount getAccountUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userAccountMapper.getAccountUser(map);
	}

	@Override
	public boolean addUserAccount(UserAccount userAccount) {
		try {
			userAccountMapper.addUserAccount(userAccount);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(UserAccount userAccount) {
		try {
			userAccountMapper.update(userAccount);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Map<String, Object> map) {
		try {
			userAccountMapper.delete(map);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
