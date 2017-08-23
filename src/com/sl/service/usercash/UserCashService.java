package com.sl.service.usercash;

import java.util.List;
import java.util.Map;

import com.sl.pojo.UserCash;

public interface UserCashService {
	public int getUserCashCount(Map<String,Object> map);
    public List<UserCash> getUserCashList(Map<String,Object> map);
    public boolean add(UserCash userCash);
}
