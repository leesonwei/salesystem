package com.sl.pojo;

import java.util.List;

import com.sl.pojo.Function;
/**
 * 角色表与功能表关系实体类
 **/
public class RoleFunctions {
	/**
	 * 成员变量
	 * */
	private Function mainFunction;        //au_function数据表的实体类
	private List<Function> subFunctions;  //功能表的子类
	
	/**
	 * 封装方法
	 * */
	public Function getMainFunction() {
		return mainFunction;
	}
	
	public void setMainFunction(Function mainFunction) {
		this.mainFunction = mainFunction;
	}
	
	public List<Function> getSubFunctions() {
		return subFunctions;
	}
	
	public void setSubFunctions(List<Function> subFunctions) {
		this.subFunctions = subFunctions;
	}
	
}
