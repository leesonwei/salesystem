package com.sl.dao.baseaccount;

import java.util.List;
import java.util.Map;

import com.sl.pojo.UserBaseAccountLog;

public interface BaseAccountMapper {
    public int getBaseAccountLogCount(Map<String,Object> map);
    public List<UserBaseAccountLog> getBaseAccountLogList(Map<String,Object> map);
    public boolean add(UserBaseAccountLog userBaseAccountLog);
}
