package com.sl.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.sl.common.Constants;
import com.sl.common.JsonDateValueProcessor;
import com.sl.common.PageSupport;
import com.sl.common.SQLTools;
import com.sl.pojo.DataDictionary;
import com.sl.pojo.Role;
import com.sl.pojo.User;
import com.sl.service.datadictionary.DataDictionaryServiceAware;
import com.sl.service.role.RoleServiceAware;
import com.sl.service.user.UserServiceAware;

@Controller
public class UserController extends BaseController {

	/**
	 * 成员变量
	 * */
	private Logger logger=Logger.getLogger(UserController.class);
			
	/**
	 * 成员变量
	 * */		
	//引入UserService
	@Resource
	private UserServiceAware userService;
	
	//引入RoleService
	@Resource
	private RoleServiceAware roleService;
	
	//引入DataDictionaryService
	@Resource
	private DataDictionaryServiceAware dataDictionaryService;
	   			
	/**
	 * 获取用户列表：分页显示
	 * @param currentpage
	 * @param s_referCode
	 * @param s_loginCode
	 * @param s_roleId
	 * @param s_isStart
	 * @param session
	 * @param model
	 * @return
	 */
	//@RequestMapping(value="/path/backend/userlist.html")
	@RequestMapping(value="/backend/userlist.html")
	public ModelAndView userList(@RequestParam(value="currentpage",required=false)Integer currentpage,
			@RequestParam(value="s_referCode",required=false)String s_referCode,
			@RequestParam(value="s_loginCode",required=false)String s_loginCode,
			@RequestParam(value="s_roleId",required=false)String s_roleId,
			@RequestParam(value="s_isStart",required=false)String s_isStart,
			HttpSession session,Model model){
		//从会话中获取基础模型
		Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		//判断处理
		if(null==baseModel){//无效
			return new ModelAndView("redirect:/");
		}else{
			//创建数据字典对象
			DataDictionary dataDictionary = new DataDictionary();
			//保存“证件类型”
			dataDictionary.setTypeCode("CARD_TYPE");
			//初始化列表变量
			List<Role> roleList=null;
			List<DataDictionary> cardTypeList=null;
			//调用业务方法，异常处理
			try{
				roleList=roleService.getRoleIdAndNameList();
				cardTypeList=dataDictionaryService.getDataDictionary(dataDictionary);
			}catch(Exception e2){
				e2.printStackTrace();
			}
			//创建用户对象
			User user = new User();
			//保存搜索值
			if(null!=s_loginCode){
				user.setLoginCode("%"+SQLTools.transfer(s_loginCode)+"%");
			}
			if(null!=s_referCode){
				user.setReferCode("%"+SQLTools.transfer(s_referCode)+"%");
			}
			if(!StringUtils.isNullOrEmpty(s_isStart)){
				user.setIsStart(Integer.parseInt(s_isStart));
			}else{
				user.setIsStart(null);
			}
			if(!StringUtils.isNullOrEmpty(s_roleId)){
				user.setRoleId(Integer.valueOf(s_roleId));
			}else{
				user.setRoleId(null);
			}
			/**
			 * 处理分页
			 * */
			PageSupport pages = new PageSupport();
			try {
				//获取总记录数
				pages.setTotalCount(userService.count(user));
			} catch (Exception e1) {				
				e1.printStackTrace();
				pages.setTotalCount(0);
			}
			//对总记录数判断处理
			if(pages.getTotalCount()>0){
				//判断当前页
				if(null!=currentpage){
					pages.setPage(currentpage);
				}
				//判断首页
				if(pages.getPage()<=0){
					pages.setPage(1);
				}
				//判断最后一页
				if(pages.getPage()>pages.getPageCount()){
					pages.setPage(pages.getPageCount());
				}
				//设置起始页
				user.setStarNum((pages.getPage()-1)*pages.getPageSize());
				//设置每页显示的记录数
				user.setPageSize(pages.getPageSize());
				//初始化用户列表变量
				List<User> userList=null;
				try {
					//调用业务逻辑方法
					userList=userService.getUserList(user);
				} catch (Exception e3) {					
					e3.printStackTrace();
					userList=null;					
				}
				pages.setItems(userList);
			}else{
				pages.setItems(null);
			}
			/**
			 * 保存模型值
			 * */
			model.addAllAttributes(baseModel);//从基础模型中获取左侧菜单列表
			model.addAttribute("page", pages);
			model.addAttribute("s_loginCode", s_loginCode);
			model.addAttribute("s_referCode", s_referCode);
			model.addAttribute("s_isStart", s_isStart);
			model.addAttribute("s_roleId", s_roleId);
			model.addAttribute("roleList",roleList);
			model.addAttribute("cardTypeList",cardTypeList);
			//页面跳转
			return new ModelAndView("/backend/userlist");
		}
	}
	
	/**
	 * ajax
	 * 根据id获取用户:查看用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backend/getuser.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object getUser(@RequestParam(value="id",required=false)String id){
		
		//初始化用户对象
		User user=null;
		//判断传递的id值是否为空
		if(id==null || id.equals("")){
			return "nodata";  //没有参数
		}else{
			try{
				//实例化用户对象
				 User nuser = new User();
				//设置用户的主键id
				nuser.setId(Integer.parseInt(id));
				//调用业务方法				
				user=userService.getUserById(nuser);				
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
			//成功，则返回json变量
		  	return user;
			
		}
	}
	
	/**
	 * ajax
	 * 修改密码
	 * @param userJson
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/backend/modifyPwd",method=RequestMethod.POST)
	@ResponseBody
	public Object modifyPassword(@RequestParam String userJson,HttpSession session){
		//获取用户会话
		User userSession = (User)session.getAttribute(Constants.SESSION_USER);
		//对传递的参数判断处理
		if(null==userJson || userJson.equals("")){
			return "nodata"; //没有任何数据需要处理
		}else{
			//创建JSON对象
			JSONObject roleObject=JSONObject.fromObject(userJson);
			//获取用户对象
			User user = (User)JSONObject.toBean(roleObject, User.class);
			//保存用户对象值
			user.setId(userSession.getId());
			user.setLoginCode(userSession.getLoginCode());
			try {
				if(null!=userService.getLoginUser(user)){
				    user.setPassword(user.getPassword2());
				    user.setPassword2(null);
				    //更新用户
				    userService.modifyUser(user);
				}else{
					return "oldpwdwrong";//原密码不正确
				}
			} catch (Exception e) {				
				e.printStackTrace();
				return "failed";  //修改密码失败！
			}
		}
		return "success";  //修改密码成功
	}
	
	/**
	 * ajax
	 * 添加用户或修改用户时：验证登录账号是否存在
	 * @param loginCode
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backend/logincodeisexit.shtml",method=RequestMethod.POST)
	@ResponseBody
	public String loginCodeIsExit(@RequestParam(value="loginCode",required=false)String loginCode,
			@RequestParam(value="id",required=false)String id){
		//日志记录
		logger.debug("kivy loginCodeIsExit loginCode===================== "+loginCode);
		logger.debug("kivy loginCodeIsExit id===================== "+id);
		//初始化结果变量
		String result="failed";
		//实例化用户对象
		User _user=new User();
		//获取参数中传值
		_user.setLoginCode(loginCode);
		//判断处理
		//if(!id.equals("-1")){//判断为新用户			
		//	_user.setId(Integer.valueOf(id));
			try{
				//调用业务方法
				if(userService.loginCodeIsExit(_user)==0){
					result="only"; //该用户名可以正常使用
				}else{
					result="repeat";//对不起，该用户名已存在
				}
			}catch(Exception e){
				e.printStackTrace();
				return result;
			}
		//}
		//返回值
		return result;
	}
	
	/**
	 * 添加用户
	 * @param session
	 * @param addUser 需要控制权限的类继承BaseController,用@ModelAttribute("addUser")User addUser
	 * @return
	 * 
	 */
	//@RequestMapping(value="/path/backend/adduser.html",method=RequestMethod.POST)
	@RequestMapping(value="/backend/adduser.html",method=RequestMethod.POST)
	public ModelAndView addUser(HttpSession session,@ModelAttribute("addUser")User addUser){
		//判断用户会话模型是否有效
		if(null==session.getAttribute(Constants.SESSION_BASE_MODEL)){
			//重定向
			return new ModelAndView("redirect:/");
		}else{
			try{
				//获取身份证号码
				String idCard = addUser.getIdCard();
				//截取身份证号码后六位数字
				String ps = idCard.substring(idCard.length()-6);
				//设置第一级密码
				addUser.setPassword(ps);
				//设置第二级密码
				addUser.setPassword2(ps);
				//设置创建时间
				addUser.setCreateTime(new Date());
				//设置推荐人
				addUser.setReferId(((User)session.getAttribute(Constants.SESSION_USER)).getId());
				//设置推荐人编码
				addUser.setReferCode(((User)session.getAttribute(Constants.SESSION_USER)).getLoginCode());
				//设置最后一次更新时间
				addUser.setLastUpdateTime(new Date());
				//调用业务方法
				userService.addUser(addUser);
			}catch(Exception e){
				e.printStackTrace();
			}
			//返回用户列表页面
			//return new ModelAndView("redirect:/path/backend/userlist.html");
			return new ModelAndView("redirect:/backend/userlist.html");
		}
	}
	
	/**
	 * 修改用户
	 * @param modifyUser
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/backend/modifyuser.html",method=RequestMethod.POST)
	public ModelAndView modifyUser(@ModelAttribute("modifyUser")User modifyUser,HttpSession session){
		//判断用户会话模型是否有效
		if(null==session.getAttribute(Constants.SESSION_BASE_MODEL)){
			//重定向
			return new ModelAndView("redirect:/");
		}else{
			try{
				//设置修改时间
				modifyUser.setLastUpdateTime(new Date());
				//调用业务方法
				userService.modifyUser(modifyUser);
			}catch(Exception e){
				e.printStackTrace();
			}
			//成功，则返回用户列表页面
			//return new ModelAndView("redirect:/path/backend/userlist.html");
			return new ModelAndView("redirect:/backend/userlist.html");
		}
	}
	
	/**
	 * ajax
	 * 删除用户
	 * @param delId
	 * @param delIdCardPicPath
	 * @param delBankPicPath
	 * @param delUserType
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/backend/deluser.shtml",method=RequestMethod.POST)
	@ResponseBody
	public String delUser(@RequestParam(value="delId",required=false) String delId,
			  @RequestParam(value="delIdCardPicPath",required=false) String delIdCardPicPath,			  
			  @RequestParam(value="delBankPicPath",required=false) String delBankPicPath,			  
			  @RequestParam(value="delUserType",required=false) String delUserType,			  
			  HttpServletRequest request,HttpSession session){
		//初始化结果变量
		String result="false";
		//创建删除用户对象
		User delUser = new User();
		//保存删除用户的id值
		delUser.setId(Integer.parseInt(delId));
		try{
			//若被删除的用户为：普通消费会员、VIP会员、加盟店  则不可被删除
			if(delUserType.equals("2") || delUserType.equals("3") || delUserType.equals("4")){
				result="noallow";
			}else{
				//已经被删除了文件
				if(this.delPic(delIdCardPicPath, delId, session, request).equals("success") && this.delPic(delBankPicPath, delId, session, request).equals("success")){
					//调用业务方法
					if(userService.deleteUser(delUser)>0)
						result="success";
			      }
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		//返回值
		return result;
	}
	
	/**
	 * ajax
	 * 根据权限id或根据角色加载用户类型列表
	 * @param s_roleId
	 * @return
	 */
	@RequestMapping(value="/backend/loadUserTypeList.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object loadUserTypeList(@RequestParam(value="s_roleId",required=false)String s_roleId){
		//初始化数据字典对象列表
		List<DataDictionary> dataDictionaryList=null;
		try{
			//实例化对象
			DataDictionary dataDictionary=new DataDictionary();
			//设置用户类型
			dataDictionary.setTypeCode("USER_TYPE");
			//调用业务方法
			dataDictionaryList=dataDictionaryService.getDataDictionary(dataDictionary);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataDictionaryList;
	}
	
	/**
	 * ajax
	 * 文件上传
	 * @param cardFile
	 * @param bankFile
	 * @param mCardFile
	 * @param mBankFile
	 * @param loginCode
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/backend/upload.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestParam(value="a_fileInputID",required=false)MultipartFile cardFile,
			@RequestParam(value="a_fileInputBank",required=false)MultipartFile bankFile,
			@RequestParam(value="m_fileInputID",required=false)MultipartFile mCardFile,
			@RequestParam(value="m_fileInputBank",required=false)MultipartFile mBankFile,
			@RequestParam(value="loginCode",required=false)String loginCode,
			HttpSession session,HttpServletRequest request){
		//日志记录
		logger.debug("开始上传文件..............");
		//获取(在环境中的)文件上传的路径
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");//File.separator相当于"/"
		//日志记录路径
		logger.debug("kivy path===========>"+path);
		//初始化数据字典列表变量
		List<DataDictionary> dataDictionaryList=null;
		//实例数据字典对象
		DataDictionary dataDictionary = new DataDictionary();
		//设置类型名称“上传个人资料附件大小”,保存对象
		dataDictionary.setTypeCode("PERSONALFILE_SIZE");
		try {
			//调用业务方法，获取数据字典的“上传个人资料附件大小”类型
			dataDictionaryList=dataDictionaryService.getDataDictionary(dataDictionary);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		//初始化上传文件的大小的变量:5M
		int filesize=500000000;
		//获取数据字典中上传文件的大小
		if(null!=dataDictionaryList){
			if(dataDictionaryList.size()==1){
				filesize=Integer.valueOf(dataDictionaryList.get(0).getValueName());
			}
		}
		/**
		 * 添加用户时：上传身份证文件的处理
		 * */
		if(null!=cardFile){
			//获取上传的原文件名称(含文件名后缀)
			String oldFileName = cardFile.getOriginalFilename();
			//去掉原文件名称的后缀
			String prefix=FilenameUtils.getExtension(oldFileName);
			//日志记录
			logger.debug("kivy cardFile prefix==========>"+prefix);
			//判断上传的文件大小是否超过规定值
			if(cardFile.getSize()>filesize){
				return "1";//"上传图片大小不得超过5M！"
			}else if(prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")){//上传图片的格式
				//生成新的文件名称
				String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_IDcard.jpg";
				//日志记录
				logger.debug("kivy cardFile new fileName==========>"+cardFile.getName());
				//创建上传至的目标文件
				File targetFile = new File(path,fileName);
				//判断目标文件的路径是否存在
				if(!targetFile.exists()){
					targetFile.mkdirs();//不存在,则创建路径
				}
				//保存文件
				try{
					cardFile.transferTo(targetFile);
				}catch(Exception e1){
					e1.printStackTrace();
				}
				//获取完整的路径
				String url = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				return url;
			}else{
				return "2";//上传图片格式不正确！
			}
		}
		
		/**
		 * 添加用户时：上传银行卡文件的处理
		 * */
		if(null!=bankFile){
			//获取上传的原文件名称(含文件名后缀)
			String oldFileName = bankFile.getOriginalFilename();
			//去掉原文件名称的后缀
			String prefix=FilenameUtils.getExtension(oldFileName);
			//日志记录
			logger.debug("kivy bankFile prefix==========>"+prefix);
			//判断上传的文件大小是否超过规定值
			if(bankFile.getSize()>filesize){
				return "1";//"上传图片大小不得超过5M！"
			}else if(prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")){//上传图片的格式
				//生成新的文件名称
				String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_bank.jpg";
				//日志记录
				logger.debug("kivy bankFile new fileName==========>"+bankFile.getName());
				//创建上传至的目标文件
				File targetFile = new File(path,fileName);
				//判断目标文件的路径是否存在
				if(!targetFile.exists()){
					targetFile.mkdirs();//不存在,则创建路径
				}
				//保存文件
				try{
					bankFile.transferTo(targetFile);
				}catch(Exception e1){
					e1.printStackTrace();
				}
				//获取完整的路径
				String url = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				return url;
			}else{
				return "2";//上传图片格式不正确！
			}
		}
		
		/**
		 * 修改用户时：上传身份证文件的处理
		 * */
		if(null!=mCardFile){
			//获取上传的原文件名称(含文件名后缀)
			String oldFileName = mCardFile.getOriginalFilename();
			//去掉原文件名称的后缀
			String prefix=FilenameUtils.getExtension(oldFileName);
			//日志记录
			logger.debug("kivy mCardFile prefix==========>"+prefix);
			//判断上传的文件大小是否超过规定值
			if(mCardFile.getSize()>filesize){
				return "1";//"上传图片大小不得超过5M！"
			}else if(prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")){//上传图片的格式
				//生成新的文件名称
				String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_IDcard.jpg";
				//日志记录
				logger.debug("kivy mCardFile new fileName==========>"+mCardFile.getName());
				//创建上传至的目标文件
				File targetFile = new File(path,fileName);
				//判断目标文件的路径是否存在
				if(!targetFile.exists()){
					targetFile.mkdirs();//不存在,则创建路径
				}
				//保存文件
				try{
					mCardFile.transferTo(targetFile);
				}catch(Exception e1){
					e1.printStackTrace();
				}
				//获取完整的路径
				String url = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				return url;
			}else{
				return "2";//上传图片格式不正确！
			}
		}
		
		/**
		 * 修改用户时：上传银行卡文件的处理
		 * */
		if(null!=mBankFile){
			//获取上传的原文件名称(含文件名后缀)
			String oldFileName = mBankFile.getOriginalFilename();
			//去掉原文件名称的后缀
			String prefix=FilenameUtils.getExtension(oldFileName);
			//日志记录
			logger.debug("kivy mBankFile prefix==========>"+prefix);
			//判断上传的文件大小是否超过规定值
			if(mBankFile.getSize()>filesize){
				return "1";//"上传图片大小不得超过5M！"
			}else if(prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")){//上传图片的格式
				//生成新的文件名称
				String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_bank.jpg";
				//日志记录
				logger.debug("kivy mBankFile new fileName==========>"+mBankFile.getName());
				//创建上传至的目标文件
				File targetFile = new File(path,fileName);
				//判断目标文件的路径是否存在
				if(!targetFile.exists()){
					targetFile.mkdirs();//不存在,则创建路径
				}
				//保存文件
				try{
					mBankFile.transferTo(targetFile);
				}catch(Exception e1){
					e1.printStackTrace();
				}
				//获取完整的路径
				String url = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				return url;
			}else{
				return "2";//上传图片格式不正确！
			}
		}
		//异常时
		return null;
						
	}
	
	/**
	 * ajax
	 * 删除上传的文件
	 * @param picpath
	 * @param id
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/backend/delpic.shtml", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String delPic(@RequestParam(value="picpath",required=false)String picpath,
			@RequestParam(value="id",required=false)String id,
			HttpSession session,HttpServletRequest request){
		//初始化结果变量
		String result = "failed";
		//判断上传的路径是否为空
		if(null==picpath || picpath.equals("")){
			return "success";
		}else{
			//去除文件路径名中的"/"符号
			String[] paths = picpath.split("/");
			//获取文件路径
			//String path = request.getSession().getServletContext().getRealPath(paths[1]+File.separator+paths[2]+File.separator+paths[3]+File.separator+paths[4]);
			String path = request.getSession().getServletContext().getRealPath(paths[1]+File.separator+paths[2]+File.separator+paths[3]);
			//创建新文件
			File file = new File(path);
			//判断文件存在
			if(file.exists())
				if(file.delete()){
					//添加用户时，删除上传的图片
					if(id.equals("0")){
						result="success";
					}else{  //修改用户时，删除上传的图片
						//实例化用户对象
						User _user=new User();
						//保存用户对象
						_user.setId(Integer.parseInt(id));//id值
						if(-1!=picpath.indexOf("_IDcard.jpg"))
							_user.setIdCardPicPath(picpath);//身份证图片路径
						else if(-1!=picpath.indexOf("_bank.jpg"))
							_user.setBankPicPath(picpath);//银行卡图片路径						
						//调用业务方法
						try{
							if(userService.delUserPic(_user)>0){
								result="success";
							}
						}catch(Exception e){
							e.printStackTrace();
							return result;
						}
					}
				}
			}
		
		//返回值
		return result;
	}
	
	
	
	
	
	
	
}
