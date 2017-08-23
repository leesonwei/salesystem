package com.sl.service.coastaccountlog;

import java.util.List;
import java.util.Map;

import com.sl.pojo.UserCoastAccountLog;

public interface CoastAccountLogService {
	 public int getCoastAccountLogCount(Map<String,Object> map);
	 public List<UserCoastAccountLog> getCoastAccountLogList(Map<String,Object> map);
	 public boolean add(UserCoastAccountLog userCoastAccountLog);
}
