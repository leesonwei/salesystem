package com.sl.service.function;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.function.FunctionMapper;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
@Service
public class FunctionServiceImpl implements FunctionServiceAware {

	@Resource
	private FunctionMapper functionMapper;
	
	/**
	 * 获取主菜单功能
	 * @param authority
	 * @return
	 */
	@Override
	public List<Function> getMainFunctionList(Authority authority) {
		
		return functionMapper.getMainFunctionList(authority);
	}

	/**
	 * 获取子菜单功能
	 * @param function
	 * @return
	 */
	@Override
	public List<Function> getSubFunctionList(Function function) {
		
		return functionMapper.getSubFunctionList(function);
	}

	/**
	 * 根据角色id获取功能列表
	 * @param authority
	 * @return
	 */
	@Override
	public List<Function> getFunctionListByRoleId(Authority authority) {
		
		return functionMapper.getFunctionListByRoleId(authority);
	}

	/**
	 * 根据父级id获取功能列表
	 * @param function
	 * @return
	 */
	@Override
	public List<Function> getSubFuncList(Function function) {		
		return functionMapper.getSubFuncList(function);
	}

	/**
	 * 首页上公告、资讯和head修改密码的功能列表
	 * @param sqlInString
	 * @return
	 */
	@Override
	public List<Function> getFunctionListByIn(String sqlInString) {		
		return functionMapper.getFunctionListByIn(sqlInString);
	}

	/**
	 * 通主键id查询功能表
	 * @param function
	 * @return
	 */
	@Override
	public Function getFunctionListById(Function function) {		
		return functionMapper.getFunctionListById(function);
	}

}
