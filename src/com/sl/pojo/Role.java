package com.sl.pojo;

import java.util.Date;
/**
 * 数据表au_role(角色表)
 **/
public class Role extends Base{
	
	/**
	 * 成员变量
	 * */
	private Integer id;         //主键id
	private String roleCode;    //角色编码
	private String roleName;    //角色名称
	private Date createDate;    //创建日期
	private Integer isStart;    //是否启用（0、未启用1、启用）	
	private String createdBy;   //创建者
	
	/**
	 * 封装方法
	 * */
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
