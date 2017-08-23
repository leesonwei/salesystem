package com.sl.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sl.common.Constants;
import com.sl.pojo.User;

/**
 * 基础控制器
 * 判断用户会话是否有效
 * */
public class BaseController {

	/**
	 * 成员变量
	 * */
	private Logger logger = Logger.getLogger(BaseController.class);
	private User currentUser;
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public User getCurrentUser(){
		//判断当前用户是否有效
		if(null==this.currentUser){
			//获取请求
			HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			//获取会话
			HttpSession session=request.getSession(false);
			//判断会话有效
			if(null!=session){
				currentUser=(User)session.getAttribute(Constants.SESSION_USER);
			}else{
				currentUser=null;
			}
		}
		return currentUser;
	}
	
	/**
	 * 设置当前用户
	 * @param currentUser
	 */
	public void setCurrentUser(User currentUser){
		this.currentUser=currentUser;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		dataBinder.registerCustomEditor(Date.class,new PropertyEditorSupport(){
			public void setAsText(String value){
				try {
					//格式化值的日期形式
					setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
				} catch (ParseException e) {					
					setValue(null);
				}
			}
			public String getAsText(){
				return new SimpleDateFormat("yyyy-MM-dd").format((Date)getValue());
			}
		});
	}
	
}
