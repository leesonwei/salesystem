package com.sl.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sl.pojo.User;
/**
 * 接口，关联UserMapper.xml
 * */
@Repository
public interface UserMapper {

	/**
	 * 登录用户:通过loginCode
	 * */
	public User getLoginUser(User user);
	
	/**
	 * 根据登录账号查询用户
	 * */
	public User getUserByLoginCode(@Param("loginCode") String loginCode);
	
	/**
	 * 查询用户：通过id
	 * */
	public User getUserById(User user);
	
	/**
	 * 登录账号是否退出
	 * */
	public int loginCodeIsExit(User user);
	
	/**
	 * 更新用户
	 * */
	public int modifyUser(User user);
	
	/**
	 * 获取用户列表：分页显示
	 * */
	public List<User> getUserList(User user);
	
	/**
	 * 获取总用户数：分页显示
	 * */
	public int count(User user);
	
	/**
	 * 添加用户
	 * */
	public int addUser(User user);
	
	/**
	 * 根据id删除用户 
	 * */
	public int deleteUser(User user);
	
	/**
	 * 修改用户角色
	 * */
	public int modifyUserRole(User user);
	
	/**
	 * 删除用户图片路径
	 * */
	public int delUserPic(User user);
	
	/**
	 * 通过搜索获取用户列表
	 * */
	public List<User> getUserListBySearch(User user);
	
}
