package com.sl.dao.role;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sl.pojo.Role;

@Repository
public interface RoleMapper {
    
	/**
	 * 获取角色id和角色名称 
	 * @return
	 */
	public List<Role> getRoleIdAndNameList();
	
	/**
	 * 获取角色列表
	 * @return
	 */
	public List<Role> getRoleList();
	
	/**
	 * 通过角色编码或角色名称获取角色
	 * @param role
	 * @return
	 */
	public Role getRoleByRoleCodeOrRoleName(Role role);
	
	/**
	 * 通过id和角色编码获取角色
	 * @param role
	 * @return
	 */
	public Role getRoleByIdAndRoleCode(Role role);
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public int addRole(Role role);
	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	public int modifyRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	public int deleteRole(Role role);
	
}
