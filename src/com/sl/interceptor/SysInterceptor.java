package com.sl.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.sl.common.Constants;
//import com.sl.common.RedisAPI;
import com.sl.pojo.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 系统拦截器类
 * @author Administrator
 *
 */
public class SysInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 成员变量
	 */
	private Logger logger = Logger.getLogger(SysInterceptor.class);
	
	//注入redis缓存接口
//	@Resource
//	private RedisAPI redisAPI;
	
	/**
	 * 重写前置方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,
							Object handler) throws Exception {  
	   //创建会话对象
	   HttpSession session = request.getSession(); 
	   //获取url路径
	   String urlPath = request.getRequestURI();
	   //从session会话中获取用户对象	   	   	   
	   User user =  ((User)session.getAttribute(Constants.SESSION_USER));
	   //判断用户会话是否有效
	   if(null == user){
		  // response.sendRedirect("/");
		   response.sendRedirect("redirect:/401.html");
		   return false;
	   }else{
		   //获取键
		 //  String keyString = "Role"+user.getRoleId() + "UrlList";
		   //首先读取redis中的值
		//   String urlsString = "url:"+redisAPI.get(keyString);
		   //判断redis中是否有值
//		   if(null != urlsString && !"".equals(urlsString) && urlsString.indexOf(urlPath) > 0){//有值
//			   return true;  
//		   }else {//无值
//			   response.sendRedirect("/401.html");
//			   return false;
//		}
		   return true;
	   }
	}
}
