package com.sl.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sl.common.Constants;
import com.sl.pojo.DataDictionary;
import com.sl.service.datadictionary.DataDictionaryServiceAware;
/**
 * 数据字典控制器
 * */
@Controller
public class DataDictionaryController extends BaseController {

	//日志记录
	private Logger logger = Logger.getLogger(DataDictionaryController.class);
	
	//引入DataDictionaryService
	@Resource
	private DataDictionaryServiceAware dataDictionaryService;
	
	/**
	 * 查看数据字典页面
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/backend/dicmanage.html")
	public ModelAndView dataDictionaryManage(HttpSession session,Model model){
		//初始化数据字典列表变量
		List<DataDictionary> dataList=null;
		//获取后台模型列表
		Map<String,Object> baseModel = (Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		//判断处理
		if(null==baseModel){
			//返回登录页面的请求
			return new ModelAndView("redirect:/");
		}else{
			try{
				//调用业务方法
				dataList=dataDictionaryService.getDataDictionaryCategory();
			}catch(Exception e){
				e.printStackTrace();
				dataList=null;
			}
			//保存数据
			baseModel.put("dataList", dataList);
			model.addAllAttributes(baseModel);
			//返回数据字典页面
			return new ModelAndView("/backend/dicmanage");
		}
				
	}
	
	/**
	 * ajax
	 * 类型代码不能重复的验证(修改)
	 * @param typeCode
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backend/typecodeisexit.shtml",produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public Object typeCodeIsExit(@RequestParam(value="typeCode",required=false)String typeCode,
			@RequestParam(value="id",required=false)String id){
		//初始化结果变量
		String result="failed"; //操作超时!
		//初始化数据字典对象
		DataDictionary _dataDictionary = new DataDictionary();
		//设置“类型编码”
		_dataDictionary.setTypeCode(typeCode);
		//设置id
		if(!id.equals("-1")){
			_dataDictionary.setId(Integer.parseInt(id));
		}
		try{
			//调用业务逻辑方法
			if(dataDictionaryService.typeCodeOrValueIdIsExit(_dataDictionary)==0){
				result="only";  //该类型代码可以正常使用
			}else{
				result="repeat"; //对不起，该类型代码已存在。
			}
		}catch(Exception e){
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	/**
	 * 获取对应的数据字典信息
	 * @param tcode
	 * @return
	 */
	@RequestMapping(value = "/backend/getJsonDic.shtml", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getJsonDic(@RequestParam String tcode){
		//初始化数据字典变量
		List<DataDictionary> ddList = null;
		//判断类型编码是否为空
		if(null == tcode || "".equals(tcode)){
			return "nodata";
		}else{
			try {
				//实例化数据字典对象				
				DataDictionary dataDictionary = new DataDictionary();
				dataDictionary.setTypeCode(tcode);
				ddList = dataDictionaryService.getDataDictionary(dataDictionary);
				//判断处理
				if(null != ddList){
					return JSONArray.fromObject(ddList).toString();
				}else{
					return "nodata";
				}
			} catch (Exception e) {				
				e.printStackTrace();
				return "failed";
			}
		}
	}
	
	/**
	 * ajax
	 * 添加数据字典
	 * @param session
	 * @param dic
	 * @return
	 */
	@RequestMapping(value="/backend/addDic.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object addDic(HttpSession session,@RequestParam String dic){
		//判断传递过来的参数是否为空
		if(null == dic || "".equals(dic)){
			return "nodata";  //对不起，没有任何数据需要处理！请重试。
		}else{
			//json数据进行转换
			JSONObject roleObject = JSONObject.fromObject(dic);
			DataDictionary dataDictionary =  (DataDictionary)JSONObject.toBean(roleObject, DataDictionary.class);
			//异常处理
			try {
				//实例化数据字典对象
				DataDictionary _dataDictionarynew = new DataDictionary();
				_dataDictionarynew.setTypeCode(dataDictionary.getTypeCode());
				//根据类型编码获取对应的数据字典列表
				List<DataDictionary> ddList = dataDictionaryService.getDataDictionary(_dataDictionarynew);
				//typeCode 不能重复
				if(null != ddList && ddList.size() > 0){
					return "rename"; //数据字典添加失败！类型代码不能重复，请重试。
				}else{
					_dataDictionarynew.setTypeCode(null);
					_dataDictionarynew.setTypeName(dataDictionary.getTypeName());
					ddList = null;
					ddList = dataDictionaryService.getDataDictionary(_dataDictionarynew);
					if(null != ddList  && ddList.size() > 0){
						return "rename";
					}else{
						dataDictionaryService.addDataDictionary(dataDictionary);
					}
				}
			} catch (Exception e) {		
				e.printStackTrace();
				return "failed"; //数据字典添加失败！请重试。
			}
			return "success"; //请求"/backend/dicmanage.html"
		}
		
	}
	
	/**
	 * ajax
	 * 点击“添加”子菜单按钮
	 * @param session
	 * @param dic
	 * @return
	 */
	@RequestMapping(value="/backend/addDicSub.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object addDicSub(HttpSession session,@RequestParam String dic){
		//判断传递过来的参数是否为空
		if(null == dic || "".equals(dic)){
			return "nodata"; //对不起，没有任何数据需要处理！请重试。
		}else{
			//json数据进行转换
			JSONObject roleObject = JSONObject.fromObject(dic);
			DataDictionary dataDictionary =  (DataDictionary)JSONObject.toBean(roleObject, DataDictionary.class);
			//异常处理
			try {
				    if(dataDictionaryService.typeCodeOrValueIdIsExit(dataDictionary) > 0){//valueId重名
				    	return "rename"; //数据字典添加失败！该类型代码下的数据ID不能重复，请重试。
				    }else{
				    	int valueId = dataDictionaryService.maxValueId(dataDictionary)+1;
				    	dataDictionary.setValueId(valueId);
				    	dataDictionaryService.addDataDictionary(dataDictionary);
				    }
			} catch (Exception e) {				
				e.printStackTrace();
				return "failed"; //数据字典添加失败！请重试。
			}
			return "success"; //数据字典添加成功。^_^。
		}
		
	}
	
	/**
	 * ajax
	 * 修改1
	 * @param session
	 * @param olddic
	 * @param newdic
	 * @return
	 */
	@RequestMapping(value="/backend/modifylDic.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object modifylDic(HttpSession session,@RequestParam String olddic,@RequestParam String newdic){
		//对传递过来的两值判断是否为空
		if(null == newdic || "".equals(newdic) || "".equals(olddic) || "".equals(olddic)){
			return "nodata"; //对不起，没有任何数据需要处理！请重试。
		}else{
			//json对象转换
			JSONObject newDicObject = JSONObject.fromObject(newdic);
			JSONObject oldDicObject = JSONObject.fromObject(olddic);
			DataDictionary newDataDictionary =  (DataDictionary)JSONObject.toBean(newDicObject, DataDictionary.class);
			DataDictionary oldDataDictionary =  (DataDictionary)JSONObject.toBean(oldDicObject, DataDictionary.class);
			//异常处理
			try {
				//初始化数据字典列表对象
				List<DataDictionary> ddList = null;
				//实例化数据字典对象
				DataDictionary _ddDataDictionary  = new DataDictionary();
				//第一个typeName是新的typeCode 
				_ddDataDictionary.setTypeName(newDataDictionary.getTypeCode());
				//第二个typeCode是旧的typeCode
				_ddDataDictionary.setTypeCode(oldDataDictionary.getTypeCode());
				//调用业务方法
				ddList = dataDictionaryService.getDataDictionaryNotIn(_ddDataDictionary);
				//判断是否有重名
				if(ddList !=null && ddList.size() > 0){
					return "rename"; //数据字典添加失败！类型代码不能重复，请重试。
				}else{
					newDataDictionary.setValueName(oldDataDictionary.getTypeCode());
					dataDictionaryService.modifyDataDictionaryByTypeCode(newDataDictionary);
				}
			} catch (Exception e) {		
				e.printStackTrace();
				return "failed"; //数据字典修改失败！请重试。
			}
			return "success"; //请求"/backend/dicmanage.html"
		}
		
	}
	
	/**
	 * ajax
	 * 修改
	 * @param dicJson
	 * @return
	 */
	@RequestMapping(value = "/backend/modifyDic.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object modifyDic(@RequestParam String dicJson){
		//判断传递过来的参数是否为空
		if(null == dicJson || "".equals(dicJson)){
			return "nodata"; //对不起，没有任何数据需要处理！请重试。
		}else{
			try {
				//json对象转换
				JSONObject dicObject = JSONObject.fromObject(dicJson);
				DataDictionary dataDictionary =  (DataDictionary)JSONObject.toBean(dicObject, DataDictionary.class);
				//验证valueId重名
				if(dataDictionaryService.typeCodeOrValueIdIsExit(dataDictionary) > 0){
			    	return "rename"; //添加失败！该类型代码下的数据ID不能重复，请重试。
			    }else
			    	dataDictionaryService.modifyDataDictionaryByTypeCode(dataDictionary);//?
				return "success";  ////请求"/backend/dicmanage.html"
			} catch (Exception e) {			
				e.printStackTrace();
				return "failed";
			}
		}
	}
	
	/**
	 * ajax
	 * 删除主数据字典
	 * @param session
	 * @param dic
	 * @return
	 */
	@RequestMapping(value="/backend/delMainDic.shtml",method=RequestMethod.POST)
	@ResponseBody
	public Object delDic(HttpSession session,@RequestParam String dic){
		//判断传递过来的参数
		if(null == dic || "".equals(dic)){
			return "nodata"; //对不起，没有任何数据需要处理！请重试。
		}else{
			//json对象转换
			JSONObject roleObject = JSONObject.fromObject(dic);
			DataDictionary dataDictionary =  (DataDictionary)JSONObject.toBean(roleObject, DataDictionary.class);
			//异常处理
			try {
				//调用业务方法
				 dataDictionaryService.deleteDataDictionary(dataDictionary);
			} catch (Exception e) {				
				e.printStackTrace();
				return "failed"; //数据字典删除失败！请重试。
			}
			return "success"; //请求"/backend/dicmanage.html"
		}
		
	}
	
	/**
	 * ajax
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/backend/delDic.shtml", produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public Object delDic(@RequestParam Integer id){
		//判断传递过来的参数
		if(null == id || id<=0){
			return "nodata";
		}else{
			try {
				//实例化数据字典对象
				DataDictionary dataDictionary = new DataDictionary();
				dataDictionary.setId(id);
				logger.debug("kivy dataDictionary.getId()============>" + dataDictionary.getId());
				//调用业务方法
				dataDictionaryService.deleteDataDictionary(dataDictionary);
				return "success";
			} catch (Exception e) {			
				e.printStackTrace();
				return "failed";
			}
		}
	}
	
	
}
