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
import net.sf.json.JSONSerializer;
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
import com.sl.pojo.DataDictionary;
import com.sl.pojo.GoodsPack;
import com.sl.pojo.GoodsPackAffiliated;
import com.sl.pojo.User;
import com.sl.service.datadictionary.DataDictionaryServiceAware;
import com.sl.service.goodspack.GoodsPackServiceAware;
import com.sl.service.goodspackaffiliated.GoodsPackAffiliatedServiceAware;

/**
 * 关于商品套餐的控制器
 * */
@Controller
public class GoodsPackController extends BaseController {

	//日志记录
	private Logger logger = Logger.getLogger(GoodsPackController.class);
	
	//引入GoodsPackService
	@Resource
	private GoodsPackServiceAware goodsPackService;
	
	//引入DataDictionaryService
	@Resource
	private DataDictionaryServiceAware dataDictionaryService;
	
	//引入GoodsPackAffiliatedServiceAware
	@Resource
	private GoodsPackAffiliatedServiceAware goodsPackAffiliatedService;
	
	/**
	 * 显示商品套餐列表：分页
	 * @param currentpage
	 * @param s_goodsPackName
	 * @param s_typeId
	 * @param s_state
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/backend/goodspacklist.html")
	public ModelAndView goodsPackList(@RequestParam(value="currentpage",required=false)Integer currentpage,
			@RequestParam(value="s_goodsPackName",required=false)String s_goodsPackName,
			@RequestParam(value="s_typeId",required=false)String s_typeId,
			@RequestParam(value="s_state",required=false)String s_state,
			HttpSession session,Model model){
		//获取会话模型
		Map<String,Object> baseModel = (Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		//判断处理
		if(null==baseModel){
			return new ModelAndView("redirect:/");//无授权，登录请求
		}else{
			/**
			 * 处理数据字典
			 * */
			//创建数据字典对象
			DataDictionary dataDictionary = new DataDictionary();
			//获取套餐类型
			dataDictionary.setTypeCode("PACK_TYPE");
			//初始化套餐列表变量
			List<DataDictionary> packTypeList = null;
			try {
				//调用业务方法
				packTypeList = dataDictionaryService.getDataDictionary(dataDictionary);
			} catch (Exception e2) {		
				e2.printStackTrace();
			}
			/**
			 * 处理商品套餐对象（搜索）
			 * */
			//创建商品套餐对象
			GoodsPack goodsPack = new GoodsPack();
			if(null != s_goodsPackName)
				goodsPack.setGoodsPackName("%"+SQLTools.transfer(s_goodsPackName)+"%");
			if(!StringUtils.isNullOrEmpty(s_state))
				goodsPack.setState(Integer.valueOf(s_state));
			else 
				goodsPack.setState(null);
			if(!StringUtils.isNullOrEmpty(s_typeId))
				goodsPack.setTypeId(Integer.valueOf(s_typeId));
			else 
				goodsPack.setTypeId(null);
			/**
			 * 处理分页
			 * */
			//创建分页对象
			PageSupport page = new PageSupport();
			
			try{
				//总记录数
				page.setTotalCount(goodsPackService.count(goodsPack));
			}catch (Exception e1) {				
				e1.printStackTrace();
				page.setTotalCount(0);//无记录数
			}
			if(page.getTotalCount() > 0){
				if(currentpage != null)
					page.setPage(currentpage);//当前页
				if(page.getPage() <= 0)
					page.setPage(1);//首页
				if(page.getPage() > page.getPageCount())
					page.setPage(page.getPageCount());//尾页
				//设置初始页
				goodsPack.setStarNum((page.getPage() - 1) * page.getPageSize());
				//设置每页记录数
				goodsPack.setPageSize(page.getPageSize());
				//初始化商品套餐列表变量
				List<GoodsPack> goodsPackList = null;
				try {
					//调用业务方法
					goodsPackList = goodsPackService.getGoodsPackList(goodsPack);
				}catch (Exception e) {					
					e.printStackTrace();
					goodsPackList = null;					
				}
				//设置项目分页
				page.setItems(goodsPackList);
			}else{
				page.setItems(null);
			}
			/**
			 * 保存结果数据
			 * */
			model.addAllAttributes(baseModel);
			model.addAttribute("page", page);
			model.addAttribute("packTypeList", packTypeList);
			model.addAttribute("s_goodsPackName", s_goodsPackName);
			model.addAttribute("s_typeId", s_typeId);
			model.addAttribute("s_state", s_state);
			//跳转页面
			return new ModelAndView("/backend/goodspacklist");
		}
	}
	
	/**
     * 封装将json对象转换为java集合对象
     * 
     * @param <T>
     * @param clazz
     * @param jsons 
     * @return
     */
    private <T> List<T> getJavaCollection(T clazz, String jsons) {
        List<T> objs=null;
        JSONArray jsonArray=(JSONArray)JSONSerializer.toJSON(jsons);
        if(jsonArray.size() > 1){
            objs=new ArrayList<T>();
            List list=(List)JSONSerializer.toJava(jsonArray);
            for(int i = 0; i < list.size()-1; i++){
            	JSONObject jsonObject=JSONObject.fromObject(list.get(i));
            	T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }
	
    /**
     * ajax
     * 添加或修改时：验证套餐编码是否存在
     * @param goodsPackCode
     * @param id
     * @return
     */
    @RequestMapping(value = "/backend/goodspackcodeisexit.shtml", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String goodsPackCodeIsExit(@RequestParam(value="goodsPackCode",required=false) String goodsPackCode,
								  @RequestParam(value="id",required=false) String id){
    	//日志记录
		logger.debug("kivy goodsPackCodeIsExit goodsPackCode =====================> "+goodsPackCode);
		logger.debug("kivy goodsPackCodeIsExit id =====================> "+id);
		//初始化结果变量
		String result = "failed";
		//实例化商品套餐对象
		GoodsPack _goodsPack = new GoodsPack();
		_goodsPack.setGoodsPackCode(goodsPackCode);
		if(!id.equals("-1"))
			_goodsPack.setId(Integer.valueOf(id)); //不属于“添加”
		try {
			if(goodsPackService.goodsPackCodeIsExit(_goodsPack) == 0)
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
     * ajax
     * 修改状态
     * @param session
     * @param state
     * @return
     */
    @RequestMapping("/backend/modifygoodspackstate.shtml")
	@ResponseBody
	public Object modifyGoodsPackState(HttpSession session,@RequestParam String state){
		//判断传递过来的数据是否为空
    	if(null == state || "".equals(state)){
			return "nodata";
		}else{
			//将json对象转换
			JSONObject goodsPackObject = JSONObject.fromObject(state);
			GoodsPack stateObjGoodsPack =  (GoodsPack)JSONObject.toBean(goodsPackObject, GoodsPack.class);
			try {
				//调用业务方法
				goodsPackService.modifyGoodsPack(stateObjGoodsPack);
			} catch (Exception e) {				
				return "failed";
			}
			return "success";
		}
	}
    
    /**
     * 跳转到添加商品的页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value="/backend/addgoodspack.html")
    public ModelAndView addGoodsPack(HttpSession session,Model model){
    	//获取会话模型
    	Map<String,Object> baseModel = (Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
    	//判断处理
    	if(null==baseModel){
    		return new ModelAndView("redirect:/");//无效，则跳转到登录页面
    	}else{
    		//创建数据字典对象
    		DataDictionary dataDictionary = new DataDictionary();
    		//设置套餐类型
    		dataDictionary.setTypeCode("PACK_TYPE");
    		//初始化列表变量
    		List<DataDictionary> packTypeList = null;
    		try {
				//调用业务方法获取套餐类型列表
				packTypeList = dataDictionaryService.getDataDictionary(dataDictionary);
			} catch (Exception e) {				
				e.printStackTrace();
			}
    		//保存相关值
    		 model.addAllAttributes(baseModel);
    		 model.addAttribute("packTypeList",packTypeList);
    		 //跳转到添加商品套餐页面
    		 return new ModelAndView("/backend/addgoodspack");
    	}
    }
    
    /**
     * 添加商品套餐
     * @param session
     * @param addGoodsPack
     * @return
     */
    @RequestMapping(value="/backend/saveaddgoodspack.html",method=RequestMethod.POST)
    public ModelAndView addGoodsPack(HttpSession session,@ModelAttribute("addGoodsPack")GoodsPack addGoodsPack){
    	//判断会话模型是否有效
    	if(session.getAttribute(Constants.SESSION_BASE_MODEL)==null){
    		return new ModelAndView("redirect:/");//无效，则重新登录
    	}else{
    		//需要异常处理
    		try {
    			//从会话中获取用户对象
    			User user = (User)session.getAttribute(Constants.SESSION_USER);
    			//初始化商品套餐附属列表
    			List<GoodsPackAffiliated> gpaList = null;
    			//封装将json对象转换为java集合对象
				gpaList = getJavaCollection(new GoodsPackAffiliated(),addGoodsPack.getGoodsJson());
				//保存商品套餐的相关数据
				addGoodsPack.setCreateTime(new Date());//创建日期
				addGoodsPack.setCreatedBy(user.getLoginCode());//创建者
				addGoodsPack.setLastUpdateTime(new Date());
				//调用业务方法 
				goodsPackService.hl_addGoodsPack(addGoodsPack, gpaList);
			} catch (Exception e) {				
				e.printStackTrace();
			}
    		//发送跳转到查看商品套餐列表的页面的请求
    		return new ModelAndView("redirect:/backend/goodspacklist.html");
    	}
    }
    
    /**
     * 跳转到更新商品套餐的页面
     * @param model
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/backend/modifygoodspack.html")
	public ModelAndView modifyGoodsPack(Model model,HttpSession session,@RequestParam(value="id",required=false) String id){
    	//获取会话模型
    	Map<String,Object> baseModel= (Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		//判断处理
    	if(baseModel == null){
			return new ModelAndView("redirect:/");//无效，则跳转到登录页面
		}else{
			//判断传递的id值是否为空
			if(null == id || id.equals("")){
				return new ModelAndView("redirect:/backend/goodspacklist.html");//返回商品套餐列表的请求
			}
			//实例化数据字典对象
			DataDictionary dataDictionary = new DataDictionary();
			//获取套餐类型
			dataDictionary.setTypeCode("PACK_TYPE");
			//初始化列表的变量
			List<DataDictionary> packTypeList = null;
			List<GoodsPackAffiliated> goodsList = null;
			//实例化套餐附属对象
			GoodsPackAffiliated goodsPackAffiliated = new GoodsPackAffiliated();
			goodsPackAffiliated.setGoodsPackId(Integer.valueOf(id));
			try {
				//调用业务方法
				packTypeList = dataDictionaryService.getDataDictionary(dataDictionary);
				goodsList = goodsPackAffiliatedService.getGoodsPackAffiliatedListById(goodsPackAffiliated);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			//实例化商品套餐对象
			GoodsPack goodsPack = new GoodsPack();
			goodsPack.setId(Integer.valueOf(id));
			try {
				//调用业务方法
				goodsPack = goodsPackService.getGoodsPackById(goodsPack);				
			} catch (Exception e) {			
				e.printStackTrace();
			}
			//保存相关值
			model.addAllAttributes(baseModel);
			model.addAttribute("packTypeList", packTypeList);
			model.addAttribute("goodsList", goodsList);
			model.addAttribute("goodsPack", goodsPack);
			//跳转到更新商品套餐的页面
			return new ModelAndView("/backend/modifygoodspack");
		}
	}
    
    /**
     * 根据id值获取商品套餐信息
     * @param id
     * @return
     */
    @RequestMapping(value="/backend/getGoodsPack.shtml",produces={"text/html;charset=UTF-8"})
    @ResponseBody
    public Object getGoodsPack(Model model,@RequestParam(value="id",required=false)String id){
    	//初始化json变量
    	String cjson="";
    	//对传递过 来的值 进行判断
    	if(null==id || id.equals("")){
    		return "nodata";
    	}else{
    		try {
				//实例化商品套餐对象
				GoodsPack goodsPack = new GoodsPack();
				//保存id值
				goodsPack.setId(Integer.parseInt(id));
				//调用业务方法 
				goodsPack = goodsPackService.getGoodsPackById(goodsPack);
				//封装成json对象
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
				JSONObject jo = JSONObject.fromObject(goodsPack, jsonConfig);
				cjson = jo.toString();
			} catch (NumberFormatException e) {				
				e.printStackTrace();
				return "failed";
			}
    		return cjson;
    	}
    }
    
    /**
     * 修改商品套餐
     * @param session
     * @param modifyGoodsPack
     * @return
     */
	@RequestMapping(value="/backend/savemodifygoodspack.html",produces={"text/html;charset=UTF-8"})
	public ModelAndView modifyGoodsPack(HttpSession session,@ModelAttribute("modifyGoodsPack")GoodsPack modifyGoodsPack){
		//判断会话模型是否有效
				if(session.getAttribute(Constants.SESSION_BASE_MODEL) == null){
					return new ModelAndView("redirect:/");//无效，跳转到登录页面
				}else{
					try {
						//获取创建者
						User user = (User)session.getAttribute(Constants.SESSION_USER);
						//初始化商品套餐附属对象
						List<GoodsPackAffiliated> gpaList = null;
						//封装将json对象转换为java集合对象
						gpaList = getJavaCollection(new GoodsPackAffiliated(),modifyGoodsPack.getGoodsJson());
						//保存更新时间
						modifyGoodsPack.setLastUpdateTime(new Date());
						//调用业务方法
						goodsPackService.hl_modifyGoodsPack(modifyGoodsPack, gpaList);
					} catch (Exception e) {				
						e.printStackTrace();
					}
					//查看商品列表的请求
					return new ModelAndView("redirect:/backend/goodspacklist.html");
				}
	}
	
	/**
	 * ajax
	 * 删除商品套餐
	 * @param delId
	 * @return
	 */
	@RequestMapping(value="/backend/delgoodspack.shtml",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String delGoodsPack(@RequestParam(value="delId",required=false)String delId){
		//初始化结果变量
		String result="false";
		//实例化商品套餐对象
		GoodsPack delGoodsPack = new GoodsPack();
		//设置要删除的商品套餐的id值
		delGoodsPack.setId(Integer.parseInt(delId));
		try{
			//调用业务方法
			if(goodsPackService.deleteGoodsPack(delGoodsPack)>0){
				//更新商品套餐附属表
				GoodsPackAffiliated gpa = new GoodsPackAffiliated();
				gpa.setGoodsPackId(Integer.valueOf(delId));
				goodsPackAffiliatedService.deleteGoodPackAffiliated(gpa);
				//设置结果变量
				result="success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ajax
	 * 获取套餐类型列表和相关商品列表
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backend/getPackTypeListAndGoodsList.shtml",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getPackTypeListAndGoodsList(Model model,@RequestParam(value="id",required=false) String id){
		//初始化结果变量
		String result="";
		List<DataDictionary> packTypeList = null;
		List<GoodsPackAffiliated> goodsList = null;		
		if(!id.equals("-1"))			
		try {
			//创建Map集合
			Map<String,Object> map = new HashMap<String,Object>();
			//创建数据字典对象
			DataDictionary dataDictionary = new DataDictionary();
			//设置套餐类型
			dataDictionary.setTypeCode("PACK_TYPE");
			//创建套餐附属对象
			GoodsPackAffiliated goodsPackAffiliated = new GoodsPackAffiliated();
			goodsPackAffiliated.setGoodsPackId(Integer.valueOf(id));
			//调用业务方法
			packTypeList = dataDictionaryService.getDataDictionary(dataDictionary);
			goodsList = goodsPackAffiliatedService.getGoodsPackAffiliatedListById(goodsPackAffiliated);
			//保存值
			map.put("packTypeList", packTypeList);
			map.put("goodsList", goodsList);
			//设置返回值
			result="success";
		} catch (Exception e) {			
			e.printStackTrace();
			result="failed";
		}
		return result;
		
	}
	
	
	
	
	
}
