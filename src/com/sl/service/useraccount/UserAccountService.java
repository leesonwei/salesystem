package com.sl.service.useraccount;

import java.util.Map;

import com.sl.pojo.UserAccount;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

public interface UserAccountService {
	   public UserAccount getAccountUser(Map<String,Object> map);
	   public boolean addUserAccount(UserAccount userAccount);
	   public boolean update(UserAccount userAccount);
	   public boolean delete(Map<String,Object> map);
}
