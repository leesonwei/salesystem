package com.sl.pojo;

import java.util.List;
/**
 * MessageReply
 * 留言回复关联类
 **/
public class MessageReply{
	/**
	 * 成员变量
	 * */
	private LeaveMessage leaveMessage;   //留言表
	private List<Reply> replyList;       //回复列表
	
	/**
	 * 封装方法
	 * */
	public LeaveMessage getLeaveMessage() {
		return leaveMessage;
	}
	public void setLeaveMessage(LeaveMessage leaveMessage) {
		this.leaveMessage = leaveMessage;
	}
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	

}
