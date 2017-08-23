package com.sl.service.authority;

import java.util.List;

import com.sl.pojo.Authority;

public interface AuthorityServiceAware {

	/**
	 * 根据角色id获取权限列表
	 * @param authority
	 * @return
	 */
	public List<Authority> getList(Authority authority);
	
	/**
	 * 根据角色id和功能表id获取权限
	 * @param authority
	 * @return
	 */
	public Authority getAuthority(Authority authority);
	
	/**
	 * 添加权限
	 * @param authority
	 * @return
	 */
	public int addAuthority(Authority authority);
	
	/**
	 * 修改权限
	 * @param authority
	 * @return
	 */
	public int modifyAuthority(Authority authority);
	
	/**
	 * 删除权限
	 * @param authority
	 * @return
	 */
	public int deleteAuthority(Authority authority);
	
	/**
	 * 添加权限的提示
	 * @param ids
	 * @param createdBy
	 * @return
	 */
	public boolean hl_addAuthority(String[] ids,String createdBy);
	
	/**
	 * 删除添加权限的提示
	 * @param authority
	 * @param checkFuncList
	 * @return
	 */
	public boolean hl_delAddAuthority(Authority authority,String checkFuncList);
	
}
