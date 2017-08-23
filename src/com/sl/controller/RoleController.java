package com.sl.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sl.common.Constants;
import com.sl.pojo.Role;
import com.sl.pojo.User;
import com.sl.service.role.RoleServiceAware;
import com.sl.service.user.UserServiceAware;

/**
 * 角色控制器
 * */
@Controller
public class RoleController extends BaseController {
      
	//声明日志记录
	private Logger logger = Logger.getLogger(RoleController.class);
	
	//引入UserService
	@Resource
	private UserServiceAware userService;
	
	//引入RoleService
	@Resource
	private RoleServiceAware roleService;
	
	/**
	 * 查看角色列表
	 * @param session
	 * @param model
	 * @return
	 * 注意：ModelAndView可以说是视图和模型的结合体，但是在每次使用的时候都需要我们自己创建，并将想要目的页面的逻辑视图名作为参数，并通过addObject()方法添加属性。
	 *      Model是每个请求中都会自带的，我们可以通过addAttribute()方法添加属性，在跳转页面时直接返回该页面的逻辑视图名。
	 */
	@RequestMapping("/backend/rolelist.html")
	public ModelAndView roleList(HttpSession session,Model model){
		//初始化角色列表
		List<Role> roleList = null;
		//获取后台模型列表
		Map<String,Object> baseModel = (Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		//判断处理
		if(null==baseModel){
			return new ModelAndView("redirect:/");
		}else{
			try {
				//调用业务方法
				roleList=roleService.getRoleList();
			} catch (Exception e) {				
				e.printStackTrace();
				roleList=null;
			}
			//保存数据
			model.addAllAttributes(baseModel);
			model.addAttribute(roleList);
			//返回角色列表页面
			return new ModelAndView("/backend/rolelist");
		}
	}
	
	/**
	 * ajax
	 * 新增角色
	 * @param session
	 * @param role
	 * @return
	 */
	@RequestMapping("/backend/addRole.shtml")
	@ResponseBody
	public Object addRole(HttpSession session,@RequestParam String role){
		//判断传递过来的json角色对象
		if(null==role || role.equals("")){
			return "nodata"; //对不起，没有任何数据需要处理！请重试。
		}else{
			//把json角色对象转换为role对象
			JSONObject roleObject = JSONObject.fromObject(role);
			Role _role = (Role)JSONObject.toBean(roleObject, Role.class);
			//设置创建日期
			_role.setCreateDate(new Date());
			//设置启用
			_role.setIsStart(1);
			//设置创建者
			_role.setCreatedBy(((User)session.getAttribute(Constants.SESSION_USER)).getLoginCode());
			try{
				//调用业务方法验证角色是否存在
				if(null!=roleService.getRoleByRoleCodeOrRoleName(_role)){
					return "rename";//角色添加失败！角色代码和角色名称不能重复，请重试。
				}else{
					//调用业务方法新增用户
					roleService.addRole(_role);
				}
			}catch(Exception e){
				e.printStackTrace();
				return "failed"; //角色添加失败！请重试。
			}
			return "success"; //角色添加成功 ^_^ 继续添加请填写。
		}
	}
	
	/**
	 * ajax
	 * 修改角色
	 * @param role
	 * @param session
	 * @return
	 */
	@RequestMapping("/backend/modifyRole.shtml")
	@ResponseBody
	public Object modifyRole(@RequestParam String role,HttpSession session){
		//判断传递过来的json角色对象
		if(null==role || role.equals("")){
			return "nodata"; //对不起，没有任何数据需要处理！请重试。
		}else{
			//把json角色对象转换为role对象
			JSONObject roleObject = JSONObject.fromObject(role);
			Role _role = (Role)JSONObject.toBean(roleObject, Role.class);
			//设置修改的时间（即创建时间）
			_role.setCreateDate(new Date());
			//设置创建者
			_role.setCreatedBy(((User)session.getAttribute(Constants.SESSION_USER)).getLoginCode());
			try{
				//调用业务方法，修改角色
				roleService.hl_modifyRole(_role);
			}catch(Exception e){
				e.printStackTrace();
				return "failed";//角色修改失败！请重试。
			}
			return "success"; //角色修改成功 ^_^
		}
	}
	
	/**
	 * ajax
	 * 删除角色
	 * @param role
	 * @param session
	 * @return
	 */
	@RequestMapping("/backend/delRole.shtml")
	@ResponseBody
	public Object delRole(@RequestParam String role,HttpSession session){
		//初始化用记列表变量
		List<User> userList = null;
		//判断传递过来的json角色对象
		if(null==role || role.equals("")){
			return "nodata"; //对不起，没有任何数据需要处理！请重试。
		}else{
			//把json角色对象转换为role对象
			JSONObject roleObject = JSONObject.fromObject(role);
			Role _role = (Role)JSONObject.toBean(roleObject, Role.class);
			try{
				//创建用户对象
				User user = new User();
				//设置角色id
				user.setRoleId(_role.getId());
				//调用业务方法
				userList = userService.getUserListBySearch(user);
				//对结果判断处理
				if(null==userList || userList.size()==0){
					roleService.deleteRole(_role);//删除用户
				}else{
					String flag="";
					for(int i=0;i<userList.size();i++){
						flag+=userList.get(i).getLoginCode();
						flag+=",";
					}
					return flag;
				}
			}catch(Exception e){
				e.printStackTrace();
				return "failed"; //删除角色失败！请重试。
			}
			return "success"; //删除角色成功 ^_^
		}
	}
	
	
}
