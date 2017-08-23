package com.sl.pojo;

import java.util.List;
/**
 * 菜单类的实体
 * */
public class Menu{

	/**
	 * 成员变量
	 * */
	private Function mainMenu;        //功能类：主菜单
	private List<Function> subMenus;  //功能子类，子菜单

	/**
	 * 封装方法
	 * */
	public List<Function> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Function> subMenus) {
		this.subMenus = subMenus;
	}

	public Function getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(Function mainMenu) {
		this.mainMenu = mainMenu;
	}

	

	
	
}
