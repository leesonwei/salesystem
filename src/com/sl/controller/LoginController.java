package com.sl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sl.common.Constants;

import com.sl.pojo.Affiche;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
import com.sl.pojo.Information;
import com.sl.pojo.Menu;
import com.sl.pojo.User;
import com.sl.service.affiche.AfficheServiceAware;
import com.sl.service.function.FunctionServiceAware;
import com.sl.service.information.InformationServiceAware;
import com.sl.service.information2.GonggaoService;
import com.sl.service.information2.ZixunService;
import com.sl.service.user.UserServiceAware;

/**
 * 登录和注销模块
 * */
@Controller
@Scope("prototype")
public class LoginController extends BaseController {

	/**
	 * 成员变量
	 * */
	private Logger logger = Logger.getLogger(LoginController.class);
	
	//引入UserService
	@Resource
	private UserServiceAware userService;
	
	//引入FunctionMapper
	@Resource
	private FunctionServiceAware functionService;
	
	//引入AfficheMapper
	@Resource
	private AfficheServiceAware afficheService;
	
	//引入InformationMapper
	@Resource
	private InformationServiceAware informationService;
	
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping(value="/login_toLogin")
	public String toLogin(){
		return "login";
	}
	
	/**
	 * ajax
	 * 用户登录
	 * @param session
	 * @param loginCode
	 * @param password
	 * @return
	 */
	@RequestMapping("/login.html")
	@ResponseBody
	public Object login(HttpSession session,@RequestParam("loginCode") String loginCode,@RequestParam("password") String password){
		
		//初始化用户对象
		User user=null;
		//调用业务方法
		user=userService.getLoginUser(loginCode, password);
		//判断处理
		if(user==null || "".equals(user)){
			return "nodata";
		}else{						
			try {
				session.setAttribute(Constants.SESSION_USER, user);						
				return "success";
			} catch (Exception e) { //出现异常时
				return "failed";
			}					
		}
	}
	
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		//注销用户会话对象
		session.removeAttribute(Constants.SESSION_USER);
		//会话无效
		session.invalidate();
		//当前用户对象为空
		this.setCurrentUser(null);
		//返回首页
		return "index";
	}
	
	/**
	 * 返回401无授权页面
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/401.html")
	public ModelAndView noRole(User user){
		return new ModelAndView("401");
	}
	
	/**
	 * 返回主页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/main.html",method=RequestMethod.GET)
	public String main(HttpSession session,Model model){
			 		
        //初始化信息变量
		List<Information> infoList=null;
		Information information=new Information();
		//初始化公告变量
		List<Affiche> afficheList=null;
		Affiche affiche = new Affiche();
		//异常处理
		try{
			infoList=informationService.getInformationList(information);
			afficheList=afficheService.getPortalAfficheList(affiche);
		}catch(Exception e){
			infoList=null;
			afficheList=null;
		}
		//初始化左侧菜单变量
		List<Menu> mList=null;
		//获取当前用户对象
		User user = this.getCurrentUser();
		//判断处理
		if(null!=user){
			//创建Map集合
			//Map<String,Object> model=new HashMap<String,Object>();
			//添加进集合
			//model.put("user", user);
			model.addAttribute("user", user);
			mList=getFuncByCurrentUser(user.getRoleId());
			if(null!=mList){
				JSONArray jsonArray=JSONArray.fromObject(mList);
				String jsonString=jsonArray.toString();
				//model.put("mList", jsonString);
				model.addAttribute("mList",jsonString);
			}
			//保存相关数据
			model.addAttribute("infoList", infoList);
			model.addAttribute("afficheList", afficheList);
			//保存数据到session会话
			session.setAttribute(Constants.SESSION_BASE_MODEL, model);
			//页面跳转
			//return new ModelAndView("main",model);
			return "main";
		}else{
			//return new ModelAndView("redirect:/");
			return "redirect:/";
		}
		
	}
	
	/**
	 * 根据当前用户的角色id的获取功能列表（对应菜单）
	 * @param roleId
	 * @return
	 */
	protected List<Menu> getFuncByCurrentUser(int roleId){
		//创建List对象
		List<Menu> menuList = new ArrayList<Menu>();
		//创建权限对象
		Authority authority = new Authority();
		//保存authority对象
		authority.setRoleId(roleId);
		try{
			//调用FunctionService的方法获取主菜单列表
			List<Function> mList=functionService.getMainFunctionList(authority);
			//判断处理
			if(mList!=null){
				//遍历集合
				for(Function function:mList){
					//创建菜单对象
					Menu menu=new Menu();
					//保存主菜单
					menu.setMainMenu(function);
					//保存角色id
					function.setRoleId(roleId);
					//调用FunctionService的方法获取子菜单
					List<Function> subList=functionService.getSubFunctionList(function);
					//对子菜单判断处理
					if(subList!=null){
						menu.setSubMenus(subList);
						//加入列表
						menuList.add(menu);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return menuList;
	}
	
	
	
	
	
}
