package com.sl.pojo;
/**
 * 数据表au_function(功能)
 * */
import java.util.Date;

/**
 * Function
 * @author bdqn_hl
 * @date 2014-2-21
 */
public class Function extends Base{
	
	/**
	 * 成员变量
	 * */
	private Integer id;             //主键id      
	private String functionCode;    //功能编码
	private String functionName;    //功能名称
	private String funcUrl;         //功能存放路径
	private int parentId;           //父级id
	private Date creationTime;      //创建时间
	
	
	private Integer roleId;         //角色表id
	
	
	/**
	 * 封装方法
	 * */
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}
