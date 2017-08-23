package com.sl.pojo;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 数据表leave_message
 **/
public class LeaveMessage extends Base{
	
	/**
	 * 成员变量
	 * */
	private Integer id;              //主键id
	private String messageCode;      //留言编码
	private String messageTitle;     //留言标题
	private String messageContent;   //留言内容
	private Integer state;           //状态（1、已回复0、未回复）
	private Date createTime;         //留言时间       
	private String createdBy; 		//留言用户编码
	private List<Reply> replys;       //回复内容
	/**
	 * 封装方法
	 * */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public List<Reply> getReplys() {
		return replys;
	}
	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

}
