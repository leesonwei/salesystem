package com.sl.pojo;

import java.util.Date;

/**
 * 基础类
 **/
public class Base {
	
	/**
	 * 成员变量
	 */
	private Integer id;        //主键id
	private Integer starNum;   //分页的起始行
	private Integer pageSize;  //每页显示多少行
	private Date startTime;    //起效时间
	private Date endTime;      //失效时间
	private String searchStr;  //查询字符串
	
	/**
	 * 封装方法	 
	 */	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStarNum() {
		return starNum;
	}

	public void setStarNum(Integer starNum) {
		this.starNum = starNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	
	
}
