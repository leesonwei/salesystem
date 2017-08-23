package com.sl.pojo;

import java.util.Date;
/**
 * 数据表reply
 **/
public class Reply extends Base{
	
	/**
	 * 成员变量
	 * */
	private Integer id;           //主键id
	private Integer messageId;    //留言表id
	private String replyContent;  //回复内容
	private Date createTime;      //回复时间
	private String createdBy;     //回复人编码
	
	/**
	 * 封装方法
	 * */
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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
	
}
