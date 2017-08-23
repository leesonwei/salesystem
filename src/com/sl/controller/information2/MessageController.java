package com.sl.controller.information2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.xml.ws.RespectBinding;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sl.common.Assist;
import com.sl.common.Constants;
import com.sl.controller.BaseController;
import com.sl.pojo.LeaveMessage;
import com.sl.pojo.Reply;
import com.sl.pojo.User;
import com.sl.service.information2.LeaveMessageService;
import com.sl.service.information2.ReplyService;

@Controller
@RequestMapping(value="message/")
public class MessageController extends BaseController {
	private Logger logger=Logger.getLogger(MessageController.class);
	@Resource
	private LeaveMessageService leaveMessageService;
	@Resource
	private ReplyService replyService;
	
	@RequestMapping(value="mymessage.html")
	public String message(Model model,HttpSession session){
		List<Reply> replys=new ArrayList<Reply>();
		Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		model.addAllAttributes(baseModel);
		session.setAttribute(Constants.SESSION_BASE_MODEL, session.getAttribute(Constants.SESSION_BASE_MODEL));
		Assist assist=new Assist();
		assist.setOrder("createTime", true);
		assist.setRequires(Assist.and_eq("createdBy", ((User)session.getAttribute(Constants.SESSION_USER)).getLoginCode()));
		List<LeaveMessage> messageList=leaveMessageService.selectLeaveMessage(assist);
		replys=replyService.selectReply(null);
		model.addAttribute("messageList",messageList);
		model.addAttribute("replyList",replys);
		return "information/myMessage";
	}
	
	@RequestMapping(value="addmessage.html")
	@ResponseBody
	public String addmessage(Model model,LeaveMessage leaveMessage,HttpSession session){
		leaveMessage.setCreateTime(new Date());
		leaveMessage.setCreatedBy(((User)session.getAttribute(Constants.SESSION_USER)).getLoginCode());
		leaveMessage.setState(0);
		if(leaveMessageService.insertNonEmptyLeaveMessage(leaveMessage)>0){
			model.addAttribute("leaveMessage",leaveMessage);
			model.addAttribute("addMessageRet","success");
		}else {
			model.addAttribute("addMessageRet","fail");
		}
		return JSON.toJSONString(model);
	}
	
	@RequestMapping(value="messagelist.html")
	public String messagelist(Model model,HttpSession session){
		Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		model.addAllAttributes(baseModel);
		session.setAttribute(Constants.SESSION_BASE_MODEL, session.getAttribute(Constants.SESSION_BASE_MODEL));
		Assist assist=new Assist();//未回复信息条件
		assist.setOrder("createTime", false);
		List<LeaveMessage> leaveMessages=leaveMessageService.selectLeaveMessage(assist);
		model.addAttribute("leaveMessages",leaveMessages);
		List<Reply> replys=replyService.selectReply(null);
		model.addAttribute("replyList",replys);
		return "information/messagelist";
	}
	
	@RequestMapping(value="delmessage.html")
	@ResponseBody
	public String delmessage(Model model,@RequestParam("id") Long id){
		Assist assist=new Assist();
		assist.setRequires(Assist.and_eq("messageId", String.valueOf(id)));
		if(leaveMessageService.deleteLeaveMessageById(id)>0){
			replyService.deleteReply(assist);
			model.addAttribute("delMessageRet","success");
		}else {
			model.addAttribute("delMessageRet","fail");
		}
		return JSON.toJSONString(model);
	}
	
	@RequestMapping(value="addReply.html")
	@ResponseBody
	public String addReply(Model model,Reply reply,HttpSession session){
		reply.setCreateTime(new Date());
		reply.setCreatedBy(((User)session.getAttribute(Constants.SESSION_USER)).getLoginCode());
		LeaveMessage leaveMessage=new LeaveMessage();
		if (replyService.insertNonEmptyReply(reply)>0) {
			model.addAttribute("reply",reply);
			model.addAttribute("addReplyRet","success");
		}else {
			model.addAttribute("addReplyRet","fail");
		}
		leaveMessage.setId(reply.getMessageId());;
		leaveMessage.setState(1);
		leaveMessageService.updateNonEmptyLeaveMessageById(leaveMessage);
		return JSON.toJSONString(model);
	}
	
}
