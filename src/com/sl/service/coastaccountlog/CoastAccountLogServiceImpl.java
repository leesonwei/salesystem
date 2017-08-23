package com.sl.service.coastaccountlog;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.dao.coastaccount.CoastAccountMapper;
import com.sl.pojo.UserCoastAccountLog;
@Service
public class CoastAccountLogServiceImpl implements CoastAccountLogService {
    @Autowired
    @Resource
    private CoastAccountMapper coastAccountMapper;
	@Override
	public int getCoastAccountLogCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return coastAccountMapper.getCoastAccountLogCount(map);
	}

	@Override
	public List<UserCoastAccountLog> getCoastAccountLogList(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return coastAccountMapper.getCoastAccountLogList(map);
	}

	@Override
	public boolean add(UserCoastAccountLog userCoastAccountLog) {
		try {
			coastAccountMapper.add(userCoastAccountLog);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
