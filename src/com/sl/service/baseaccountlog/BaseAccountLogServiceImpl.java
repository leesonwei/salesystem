package com.sl.service.baseaccountlog;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.sl.dao.baseaccount.BaseAccountMapper;
import com.sl.pojo.UserBaseAccountLog;

@Service
public class BaseAccountLogServiceImpl implements BaseAccountLogService {
    @Autowired
    @Resource
    private BaseAccountMapper baseAccountMapper;
	@Override
	public int getBaseAccountLogCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseAccountMapper.getBaseAccountLogCount(map);
	}

	@Override
	public List<UserBaseAccountLog> getBaseAccountLogList(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseAccountMapper.getBaseAccountLogList(map);
	}

	@Override
	public boolean add(UserBaseAccountLog userBaseAccountLog) {
		try {
			baseAccountMapper.add(userBaseAccountLog);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
