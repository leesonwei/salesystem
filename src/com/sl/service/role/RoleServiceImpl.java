package com.sl.service.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.role.RoleMapper;
import com.sl.dao.user.UserMapper;
import com.sl.pojo.Role;
import com.sl.pojo.User;

@Service
public class RoleServiceImpl implements RoleServiceAware {

	//引入RoleMapper
	@Resource
	private RoleMapper roleMapper;
	
	//引入UserMapper
	@Resource
	private UserMapper userMapper;

	/**
	 * 获取角色id和角色名称 
	 * @return
	 */
	@Override
	public List<Role> getRoleIdAndNameList() {
		
		return roleMapper.getRoleIdAndNameList();
	}

	/**
	 * 获取角色列表
	 * @return
	 */
	@Override
	public List<Role> getRoleList() {
		
		return roleMapper.getRoleList();
	}

	/**
	 * 通过角色编码或角色名称获取角色
	 * @param role
	 * @return
	 */
	@Override
	public Role getRoleByRoleCodeOrRoleName(Role role) {
		
		return roleMapper.getRoleByRoleCodeOrRoleName(role);
	}

	/**
	 * 通过id和角色编码获取角色
	 * @param role
	 * @return
	 */
	@Override
	public Role getRoleByIdAndRoleCode(Role role) {
		
		return roleMapper.getRoleByIdAndRoleCode(role);
	}

	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	@Override
	public int addRole(Role role) {
		
		return roleMapper.addRole(role);
	}

	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	@Override
	public int modifyRole(Role role) {
		
		return roleMapper.modifyRole(role);
	}

	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	@Override
	public int deleteRole(Role role) {
		
		return roleMapper.deleteRole(role);
	}

	/**
	 * 修改用户角色
	 * @param role
	 * @return
	 */
	@Override
	public boolean hl_modifyRole(Role role) {
		//更新角色表
		roleMapper.modifyRole(role);
		//获取角色表id
		int roleId = role.getId();
		//获取角色名称
		String roleName = role.getRoleName();
		//创建用户对象
		User user = new User();
		//保存对象值
		user.setRoleId(roleId);
		user.setRoleName(roleName);
		//判断处理
		if(roleName !=null && !roleName.equals("")){
			userMapper.modifyUserRole(user);
		}
		//返回结果值
		return true;
	}
	
	
	
}
