package com.sl.dao.useraccount;

import java.util.Map;

import com.sl.pojo.UserAccount;

public interface UserAccountMapper {
   public UserAccount getAccountUser(Map<String,Object> map);
   public boolean addUserAccount(UserAccount userAccount);
   public boolean update(UserAccount userAccount);
   public boolean delete(Map<String,Object> map);
}
