package com.sl.service.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sl.pojo.User;

/**
 * 用户相关业务的接口类
 * */
public interface UserServiceAware {

	/**
	 * 登录用户:通过loginCode和password
	 * */
	public User getLoginUser(String loginCode,String password);
	
	public User getLoginUser(User user);
	
	/**
	 * 登录账号是否退出
	 * */
	public int loginCodeIsExit(User user);
	
	/**
	 * 更新用户
	 * */
	public int modifyUser(User user);
	
	/**
	 * 根据登录账号查询用户
	 * */
	public User getUserByLoginCode(String loginCode);
	
	/**
	 * 获取用户列表信息：分页显示
	 * @param user
	 * @return
	 */
	public List<User> getUserList(User user);
	
	/**
	 * 获取用户记录总数：分页显示
	 * @param user
	 * @return
	 */
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
	
	/**
	 * 查询用户：通过id
	 * */
	public User getUserById(User user);
	
}
