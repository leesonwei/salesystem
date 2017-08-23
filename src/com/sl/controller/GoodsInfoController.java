package com.sl.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.sl.common.Constants;
import com.sl.common.JsonDateValueProcessor;
import com.sl.common.PageSupport;
import com.sl.common.SQLTools;
import com.sl.pojo.GoodsInfo;
import com.sl.pojo.User;
import com.sl.service.goodsinfo.GoodsInfoServiceAware;


/**
 * 商品管理的控制器
 * */
@Controller
public class GoodsInfoController extends BaseController {

	//记录日志
	private Logger logger = Logger.getLogger(GoodsInfoController.class);
	
	//引入GoodsInfoService
	@Resource
	private GoodsInfoServiceAware goodsInfoService;
	
	/**
	 * 获取商品列表：分页显示
	 * @param session
	 * @param model
	 * @param currentpage
	 * @param s_goodsName
	 * @param s_state
	 * @return
	 */
	@RequestMapping("/backend/goodsinfolist.html")
	public ModelAndView goodsInfoList(HttpSession session,Model model,
								@RequestParam(value="currentpage",required=false)Integer currentpage ,
								@RequestParam(value="s_goodsName",required=false) String s_goodsName, 
								@RequestParam(value="s_state",required=false) String s_state
								){
		//获取会话模型
		Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		//对会话模型是否有效进行判断处理
		if(baseModel == null){
			return new ModelAndView("redirect:/"); //无效，则返回登录页面
		}else{
			/**
			 * 处理搜索的表单
			 * */		
			GoodsInfo goodsInfo = new GoodsInfo();//实例化商品对象
			if(null != s_goodsName)
				goodsInfo.setGoodsName("%"+SQLTools.transfer(s_goodsName)+"%");//防止SQL注入
			if(!StringUtils.isNullOrEmpty(s_state))
				goodsInfo.setState(Integer.valueOf(s_state));
			else 
				goodsInfo.setState(null);
			
			/**
			 * 处理分页
			 * */			
			PageSupport pages = new PageSupport();//实例化分页对象			
			try{
				pages.setTotalCount(goodsInfoService.count(goodsInfo));//获取总记录数
			}catch (Exception e1) {				
				e1.printStackTrace();
				pages.setTotalCount(0);//无记录数
			}
			if(pages.getTotalCount() > 0){//有记录数
				if(currentpage != null)
					pages.setPage(currentpage);//设置当前页
				if(pages.getPage() <= 0)
					pages.setPage(1);//设置首页
				if(pages.getPage() > pages.getPageCount())
					pages.setPage(pages.getPageCount()); //设置尾页
				//商品对象中保存起始页
				goodsInfo.setStarNum((pages.getPage() - 1) * pages.getPageSize());
				//商品对象中保存每页显示的记录数
				goodsInfo.setPageSize(pages.getPageSize());
				//初始化商品信息列表的变量
				List<GoodsInfo> goodsInfoList = null;
				//调用业务方法，获取商品列表，进行异常处理
				try {
					goodsInfoList = goodsInfoService.getGoodsInfoList(goodsInfo);
				}catch (Exception e) {					
					e.printStackTrace();
					goodsInfoList = null;					
				}
				//把商品列表包装成一个分页项目
				pages.setItems(goodsInfoList);
			}else{
				pages.setItems(null);
			}
			/**
			 * 保存相关数据
			 * */
			model.addAllAttributes(baseModel);
			model.addAttribute("page", pages);
			model.addAttribute("s_goodsName", s_goodsName);
			model.addAttribute("s_state", s_state);
			//跳转到“商品列表的页面”
			return new ModelAndView("/backend/goodsinfolist");
		}
	}
	
	/**
	 * ajax
	 * 添加或修改商品时：验证商品编码是否存在
	 * @param goodsSN
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backend/goodsSNisexit.shtml", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String loginCodeIsExit(@RequestParam(value="goodsSN",required=false) String goodsSN,
								  @RequestParam(value="id",required=false) String id){
		//初始化结果变量
		String result = "failed";
		//实例化商品对象
		GoodsInfo _goodsInfo = new GoodsInfo();
		//保存输入的商品编码
		_goodsInfo.setGoodsSN(goodsSN);
		//判断为添加弹窗的页面
		if(!id.equals("-1"))
			_goodsInfo.setId(Integer.valueOf(id));
		try {
			if(goodsInfoService.goodsSNIsExit(_goodsInfo) == 0)
				result = "only";
			else 
				result = "repeat";
		} catch (Exception e) {			
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	/**
	 * 添加商品(表单上的action)
	 * @param session
	 * @param addGoodsInfo
	 * @return
	 */
	@RequestMapping(value = "/backend/addgoodsinfo.html",method=RequestMethod.POST)
	public ModelAndView addGoodsInfo(HttpSession session,@ModelAttribute("addGoodsInfo") GoodsInfo addGoodsInfo){
		//判断会话模型是否有效
		if(session.getAttribute(Constants.SESSION_BASE_MODEL) == null){
			return new ModelAndView("redirect:/"); //无效，跳转到登录页面
		}else{
			try {
				//保存创建时间
				addGoodsInfo.setCreateTime(new Date());
				//从用户会话中获取创建者登录编码,保存创建者
				addGoodsInfo.setCreatedBy(((User)session.getAttribute(Constants.SESSION_USER)).getLoginCode());
				//保存最后更新时间
				addGoodsInfo.setLastUpdateTime(new Date());
				//调用业务方法
				goodsInfoService.addGoodsInfo(addGoodsInfo);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			//查看商品列表的请求
			return new ModelAndView("redirect:/backend/goodsinfolist.html");
		}
	}
	
	/**
	 * ajax
	 * 根据id获取商品信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backend/getgoodsinfo.shtml", produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getGoodsInfo(@RequestParam(value="id",required=false) String id){
		//初始化json变量
		String cjson = "";
		//对传递的id值进行判断处理
		if(null == id || "".equals(id)){
			return "nodata";
		}else{
			try {
				//实例化商品对象
				GoodsInfo goodsInfo = new GoodsInfo();
				//保存id值
				goodsInfo.setId(Integer.valueOf(id));
				//调用业务方法
				goodsInfo = goodsInfoService.getGoodsInfoById(goodsInfo);
				//实例化json配置对象,封装成json对象
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
				JSONObject jo = JSONObject.fromObject(goodsInfo,jsonConfig);
				cjson = jo.toString();
			} catch (Exception e) {				
				e.printStackTrace();
				return "failed";
			}
				return cjson;
		}
	}
	
	/**
	 * 修改商品(表单上的action)
	 * @param session
	 * @param modifyGoodsInfo
	 * @return
	 */
	@RequestMapping(value="/backend/modifygoodsinfo.html",method=RequestMethod.POST)
	public ModelAndView modifyGoodsInfo(HttpSession session,@ModelAttribute("modifyGoodsInfo") GoodsInfo modifyGoodsInfo){
		//判断会话模型是否有效
		if(session.getAttribute(Constants.SESSION_BASE_MODEL) == null){
			return new ModelAndView("redirect:/");//无效，跳转到登录页面
		}else{
			try {
				//保存更新时间
				modifyGoodsInfo.setLastUpdateTime(new Date());
				//调用业务方法
				goodsInfoService.modifyGoodsInfo(modifyGoodsInfo);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			//查看商品列表的请求
			return new ModelAndView("redirect:/backend/goodsinfolist.html");
		}
	}
	
	/**
	 * ajax
	 * 点击状态（checked=true时，state=1）"直接勾选修改状态，立即生效"
	 * @param session
	 * @param state
	 * @return
	 */
	@RequestMapping("/backend/modifystate.shtml")
	@ResponseBody
	public Object modifyState(HttpSession session,@RequestParam String state){
		//对传递值的判断处理
		if(null == state || "".equals(state)){
			return "nodata";
		}else{
			//将json对象转换处理
			JSONObject goodsInfoObject = JSONObject.fromObject(state);
			GoodsInfo stateObjGoodsInfo =  (GoodsInfo)JSONObject.toBean(goodsInfoObject, GoodsInfo.class);
			try {
				//调用业务方法
				goodsInfoService.modifyGoodsInfo(stateObjGoodsInfo);
			} catch (Exception e) {				
				return "failed";
			}
			return "success";
		}
		
	}
	
	/**
	 * ajax
	 * 删除商品
	 * @param delId
	 * @return
	 */
	@RequestMapping(value = "/backend/delgoodsinfo.shtml", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String delGoodsInfo(@RequestParam(value="delId",required=false) String delId){
		//初始化结果变量
		String result= "false" ;
		//创建商品对象
		GoodsInfo delGoodsInfo = new GoodsInfo();
		//保存要删除商品的id值
		delGoodsInfo.setId(Integer.valueOf(delId));
		try {
			if(goodsInfoService.isExitInPack(delGoodsInfo) > 0){ //验证该商品是否已被商品套餐引用，不能删除！
				result = "isused";
			}else{
				if(goodsInfoService.deleteGoodsInfo(delGoodsInfo) > 0) //调用业务方法删除商品
					result = "success";
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
