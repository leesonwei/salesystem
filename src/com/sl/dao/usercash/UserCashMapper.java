package com.sl.dao.usercash;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sl.pojo.UserCash;

@Repository
public interface UserCashMapper {
	public int getUserCashCount(Map<String,Object> map);
    public List<UserCash> getUserCashList(Map<String,Object> map);
    public boolean add(UserCash userCash);
    
}
