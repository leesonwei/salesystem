package com.sl.dao.function;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sl.pojo.Authority;
import com.sl.pojo.Function;
@Repository
public interface FunctionMapper {
   
	/**
	 * 获取主菜单功能
	 * @param authority
	 * @return
	 */
	public List<Function> getMainFunctionList(Authority authority);
	
	/**
	 * 获取子菜单功能
	 * @param function
	 * @return
	 */
	public List<Function> getSubFunctionList(Function function);
	
	/**
	 * 根据角色id获取功能列表
	 * @param authority
	 * @return
	 */
	public List<Function> getFunctionListByRoleId(Authority authority);
	
	/**
	 * 根据父级id获取功能列表
	 * @param function
	 * @return
	 */
	public List<Function> getSubFuncList(Function function);
	
	/**
	 * 首页上公告、资讯和head修改密码的功能列表
	 * @param sqlInString
	 * @return
	 */
	public List<Function> getFunctionListByIn(@Param("sqlInString")String sqlInString);
	
	/**
	 * 通主键id查询功能表
	 * @param function
	 * @return
	 */
	public Function getFunctionListById(Function function);
	
}
