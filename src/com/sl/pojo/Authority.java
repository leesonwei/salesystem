package com.sl.pojo;

import java.util.Date;

/**
 * 数据表au_authority(权限表)
 **/
public class Authority extends Base{
	
	/**
	 * 成员变量
	 * */
	private Integer id;           //主键id
	private Integer roleId;           //角色id
	private Integer functionId;       //功能id
	private Integer userTypeId;       //用户类型id     
	private Date creationTime;    //创建时间
	private String createdBy;     //创建者
	
	/**
	 * 封装方法
	 * */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	

}
