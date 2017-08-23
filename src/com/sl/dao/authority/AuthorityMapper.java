package com.sl.dao.authority;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sl.pojo.Authority;

@Repository
public interface AuthorityMapper {

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
	
}
