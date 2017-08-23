package com.sl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.sl.common.Constants;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
import com.sl.pojo.Menu;
import com.sl.pojo.Role;
import com.sl.pojo.RoleFunctions;
import com.sl.pojo.User;
import com.sl.service.authority.AuthorityServiceAware;
import com.sl.service.function.FunctionServiceAware;
import com.sl.service.role.RoleServiceAware;

/**
 * 权限表控制器
 * */
@Controller
public class AuthorityController extends BaseController {

	//日志记录
	private Logger logger = Logger.getLogger(AuthorityController.class);
	
	//引入RoleService
	@Resource
	private RoleServiceAware roleService;
	
	//引入FunctionService
	@Resource
	private FunctionServiceAware functionService;
	
	//引入AuthorityService
	@Resource
	private AuthorityServiceAware authorityService;
	
	//引入LoginController
	@Resource
	private LoginController loginController;
	
	/**
	 * 查看权限管理页面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/backend/authoritymanage.html")
	public ModelAndView authorityManage(Model model,HttpSession session){
		//初始化角色列表变量
		List<Role> roleList=null;
		//获取后台模型
		Map<String,Object> baseModel = (Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		//判断处理
		if(null==baseModel){
			return new ModelAndView("redirect:/");
		}else{
			try{
				//调用业务方法
				roleList=roleService.getRoleIdAndNameList();
			}catch(Exception e){
				e.printStackTrace();
				roleList=null;
			}
			//保存值
			model.addAllAttributes(baseModel);
			model.addAttribute(roleList);
			//跳转到权限页面
			return new ModelAndView("/backend/authoritymanage");
		}
	}
	
	/**
	 * ajax
	 * 点击“权限列表的名称”标识
	 * @return
	 */
	@RequestMapping(value = "/backend/functions.shtml",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object functions(){
		//初始化结果变量
		String resultString = "nodata";
		//创建功能表对象
		Function function = new Function();
		try {
			function.setId(0); //初始化对象主键id
			List<Function> fList = functionService.getSubFuncList(function);
			List<RoleFunctions> rList = new ArrayList<RoleFunctions>();
			//判断处理
			if(null != fList){				
				for(Function func : fList){
					RoleFunctions rFunctions = new RoleFunctions();
					rFunctions.setMainFunction(func);
					rFunctions.setSubFunctions(functionService.getSubFuncList(func));
					rList.add(rFunctions);
				}
				resultString = JSONArray.fromObject(rList).toString();
			}
		} catch (Exception e) {
		}
		return resultString;
	}
	
	/**
	 * ajax
	 * 获取功能列表是默认值
	 * @param rid
	 * @param fid
	 * @return
	 */
	@RequestMapping(value = "/backend/getAuthorityDefault.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getAuthorityDefault(@RequestParam Integer rid,@RequestParam Integer fid){
		String resultString = "nodata";
		try {
			Authority authority = new Authority();
			authority.setRoleId(rid);
			authority.setFunctionId(fid);
			if(authorityService.getAuthority(authority) != null){
				resultString =  "success";
			}
		} catch (Exception e) {
		}
		return resultString;
	}
	
	/**
	 * ajax
	 * 点击“确定赋予权限”按钮
	 * @param session
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/backend/modifyAuthority.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object modifyAuthority(HttpSession session,@RequestParam String ids){
		//初始化结果变量
		String resultString = "nodata";
		try {
			if(null != ids){
				String[] idsArrayStrings = StringUtils.split(ids, "-");
				if(idsArrayStrings.length > 0){
					User user = (User)session.getAttribute(Constants.SESSION_USER);
					authorityService.hl_addAuthority(idsArrayStrings,user.getLoginCode());
					List<Menu> mList = null;
					mList = loginController.getFuncByCurrentUser(Integer.valueOf(idsArrayStrings[0]));
					//JSONArray jsonArray = JSONArray.fromObject(mList);
				//	redisAPI.set("menuList"+idsArrayStrings[0], jsonArray.toString());
					
					//get all role url list to redis
					Authority authority = new Authority();
					authority.setRoleId(Integer.valueOf(idsArrayStrings[0]));
					List<Function> functionList = functionService.getFunctionListByRoleId(authority);
					
					if(null != functionList || functionList.size() >= 0){
						StringBuffer sBuffer = new StringBuffer();
						for(Function f:functionList){
							sBuffer.append(f.getFuncUrl());
						}
					//	redisAPI.set("Role"+idsArrayStrings[0]+"UrlList", sBuffer.toString());
					}
					resultString = "success";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}
	
}
