package com.sl.controller.infoquery;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sl.common.Constants;
import com.sl.controller.BaseController;
import com.sl.pojo.User;
import com.sl.pojo.UserCash;
import com.sl.service.user.UserServiceAware;

@Controller
public class InfoQueryController extends BaseController {
    private Logger logger=Logger.getLogger(InfoQueryController.class);
    
    @Resource
    private UserServiceAware userServiceAware;
    
    
	/**
	 * 推荐查询
	 * @param session
	 * @param model
	 * @return
	 */
    @RequestMapping("/infoquery/recommendQuery.html")
    public String recommendQuery(Model model,HttpSession session
    		){
    	User user=new User();
    	user.setId(2);
    	user=userServiceAware.getUserById(user);//查询第一个用户：管理员
    	User user1=new User();
    	user1.setReferId(2);
    	List<User> referList=userServiceAware.getUserListBySearch(user1);//查询出管理员推荐的用户
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL); 
    	model.addAllAttributes(baseModel);
    	model.addAttribute("user",user);
    	model.addAttribute("referList",referList);
    	return "infoquery/recommendquery";
    }
    /**
	 * 根据userid查询该用户所推荐的人
	 * @param userid
	 * @param model
	 * @return
	 */
    @RequestMapping("/infoquery/referQuery")
    @ResponseBody
    public String referQuery(@RequestParam(value="userid",required=false) Integer userid){
    	if(userid!=null){
    		User user=new User();
    		user.setReferId(userid);
    		List<User> referList=userServiceAware.getUserListBySearch(user);
    		return JSONArray.toJSONString(referList);
    	}
    	return null;
    }
    /**
	 * 根据userid查询该用户信息
	 * @param id
	 * @param model
	 * @return
	 */
    @RequestMapping("/infoquery/viewInfo")
    @ResponseBody
    public Object viewInfo(Integer id){
    	User user=new User();
    	user.setId(id);   	
    	user=userServiceAware.getUserById(user);
    	return JSONObject.toJSONString(user);
    }
    
    /**
	 * 奖励查询
	 * @param session
	 * @param model
	 * @return
	 */
    @RequestMapping("/infoquery/awardQuery.html")
    public String awardQuery(Model model,HttpSession session){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL); 
    	User user=new User();
    	user.setReferId(this.getCurrentUser().getId());
    	int num=(userServiceAware.getUserListBySearch(user)).size();
    	model.addAttribute("num",num);
    	model.addAllAttributes(baseModel);
    	return "/infoquery/awardquery";
    }
    
    /**
  	 * 区域查询
  	 * @param session
  	 * @param model
  	 * @return
  	 */
      @RequestMapping("/infoquery/areaQuery.html")
      public String areaQuery(Model model,HttpSession session){
      	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL); 
      	model.addAllAttributes(baseModel);
      	return "/infoquery/areaquery";
      }
	/**
	 * 操作须知
	 * @param session
	 * @param model
	 * @return
	 */
    @RequestMapping("/infoquery/operateKnow.html")
    public String operateKnow(Model model,HttpSession session){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL); 
    	model.addAllAttributes(baseModel);
    	return "/infoquery/operaterknow";
    }
}
