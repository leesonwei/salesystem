package com.sl.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.user.UserMapper;
import com.sl.pojo.User;

/**
 * 用户相关业务的实现类
 * */
@Service
public class UserServiceImpl implements UserServiceAware {

	//引入UserMapper
	@Resource
	private UserMapper userMapper;

	/**
	 * 登录用户:通过loginCode和password
	 * */
	@Override
	public User getLoginUser(String loginCode,String password) {
		//初始化用户对象
		User user=null;
		//保存值
		/*user.setLoginCode(loginCode);
		user.setPassword(password);
		return userMapper.getLoginUser(user);*/
		//调用UserMapper方法
		user=userMapper.getUserByLoginCode(loginCode);
		//判断处理
		if(null!=user){
			//获取密码
			String pwd=user.getPassword();
			//验证密码
			if(!pwd.equals(password)){
				user=null;
			}
		}
		return user;
	}
	
	@Override
	public User getLoginUser(User user) {		
		return userMapper.getLoginUser(user);
	}

	/**
	 * 登录账号是否退出
	 * */
	@Override
	public int loginCodeIsExit(User user) {		
		return userMapper.loginCodeIsExit(user);
	}

	/**
	 * 更新用户
	 * */
	@Override
	public int modifyUser(User user) {		
		return userMapper.modifyUser(user);
	}
	
	/**
	 * 根据登录账号查询用户
	 */
	@Override
	public User getUserByLoginCode(String loginCode) {		
		return userMapper.getUserByLoginCode(loginCode);
	}

	/**
	 * 获取用户列表信息：分页显示
	 * @param user
	 * @return
	 */
	@Override
	public List<User> getUserList(User user) {
		
		return userMapper.getUserList(user);
	}

	/**
	 * 获取用户记录总数：分页显示
	 * @param user
	 * @return
	 */
	@Override
	public int count(User user) {
		
		return userMapper.count(user);
	}

	/**
	 * 添加用户
	 */
	@Override
	public int addUser(User user) {
		
		return userMapper.addUser(user);
	}

	/**
	 * 根据id删除用户 
	 * */
	@Override
	public int deleteUser(User user) {
		
		return userMapper.deleteUser(user);
	}

	/**
	 * 修改用户角色
	 * */
	@Override
	public int modifyUserRole(User user) {
		
		return userMapper.modifyUserRole(user);
	}

	/**
	 * 删除用户图片路径
	 * */
	@Override
	public int delUserPic(User user) {
		
		return userMapper.delUserPic(user);
	}

	/**
	 * 通过搜索获取用户列表
	 * */
	@Override
	public List<User> getUserListBySearch(User user) {
		
		return userMapper.getUserListBySearch(user);
	}

	/**
	 * 查询用户：通过id
	 * */
	@Override
	public User getUserById(User user) {
		
		return userMapper.getUserById(user);
	}

	
	
	
	
	
}
