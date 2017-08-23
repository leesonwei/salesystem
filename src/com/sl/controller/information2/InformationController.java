package com.sl.controller.information2;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sl.common.Constants;
import com.sl.controller.BaseController;
import com.sl.pojo.Affiche;
import com.sl.pojo.Information;
import com.sl.pojo.User;
import com.sl.service.information2.GonggaoService;
import com.sl.service.information2.ZixunService;

@Controller
@RequestMapping(value="informanage")
public class InformationController  extends BaseController{
	private Logger logger=Logger.getLogger(InformationController.class);
	@Resource
	private ZixunService informationService;
	@Resource
	private GonggaoService afficheService;
	
	//获得资讯列表
	@RequestMapping(value="information.html")
	public String information(Model model,HttpSession session){
		Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		model.addAllAttributes(baseModel);
		List<Information> informationList=informationService.selectInformation(null);
		model.addAttribute("informationList",informationList);
		return "information/information";
	}
	
	//下载中心
	@RequestMapping(value="downloadcenter.html")
	public String downloadCenter(Model model,HttpSession session){
		Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		model.addAllAttributes(baseModel);
		List<Information> informationList=informationService.selectInformation(null);
		model.addAttribute("informationList",informationList);
		return "information/downloadcenter";
	}
	
	@RequestMapping(value="getdownloadresource.html")
	@ResponseBody
	public String getDownloadResource(Model model,@RequestParam("id") Long id){
		Information information=informationService.selectInformationById(id);
		model.addAttribute("information",information);
		return JSON.toJSONString(model);
	}
	
	@RequestMapping(value="saveInfor.html",method=RequestMethod.POST)
	@ResponseBody
	public String saveInfor(@RequestParam("uploadtime")String uptime,HttpSession session,Information information) throws ParseException{
		Map<String, Object> model=new HashMap<String, Object>();
		logger.debug("start save infor");
		User user=(User)session.getAttribute(Constants.SESSION_USER);
		if (null!=user&&null!=user.getLoginCode()) {
			information.setPublisher(user.getLoginCode());
			information.setPublishtime(new Date());
			information.setUploadtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(uptime));
			if(informationService.insertInformation(information)>0){
				model.put("information",information);
				model.put("saveInforRet","success");
			}else {
				model.put("saveInforRet","fail");
				File file=new File(information.getFilepath());
				if (file.exists() && file.isFile()) {
		            if (file.delete()) {
		            	logger.debug("删除单个文件" + information.getFilename() + "成功！");
		            } else {
		            	logger.debug("删除单个文件" + information.getFilename() + "失败！");
		            }
				}
			}
		}else {
			model.put("saveInforRet","fail");
			model.put("infor","您尚未登录或者登录已经失效!请重新登录");
		}
		return JSON.toJSONString(model);
	}
	
	@RequestMapping(value="deleteInfor.html")
	@ResponseBody
	public String deleteInfor(@RequestParam("id") Long[] ids,Model model){
		int count=0;
		for (int i = 0; i < ids.length; i++) {
			count+=informationService.deleteInformationById(ids[i]);
		}
		if(ids.length==count){
			model.addAttribute("delRet","success");
		}else {
			model.addAttribute("delRet","fail");
		}
		return JSON.toJSONString(model);
	}
	
	@RequestMapping(value="updateInfor.html")
	public String updateInfor(@RequestParam("id") Long id,Model model,HttpSession session){
		Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		model.addAllAttributes(baseModel);
		model.addAttribute("information",informationService.selectInformationById(id));
		return "information/updateInfor";
	}
	
	@RequestMapping(value="updateInforToDb.html")
	@ResponseBody
	public String updateInforToDb(Information information,Model model){
		information.setPublisher("admin");
		information.setPublishtime(new Date());
		if(informationService.updateNonEmptyInformationById(information)>0){
			model.addAttribute("upRet","success");
		}else {
			model.addAttribute("upRet","fail");
		}
		return JSON.toJSONString(model);
	}
	
	@RequestMapping(value="saveUploadFile.html",method=RequestMethod.POST)
	@ResponseBody
	public String saveUploadFile(HttpSession session,HttpServletRequest request,
			@RequestParam(value="inputInforFile",required=false) MultipartFile attach,Model model){
		logger.debug("start upload****************"+attach.getName()+"****大小:"+attach.getSize());
		String idPicPath=null;
		String suffix=null;
		String uploadFileError=null;
		String result=null;
		String fileName=null;
		Random rand=new Random();
		if(!attach.isEmpty()){
			//文件保存路径?
			String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			//原文件名
			String oldFileName=attach.getOriginalFilename();
			//原文件后缀
			suffix=oldFileName.substring(oldFileName.indexOf(".")+1);
			Long fileSize=500000000000l;
			if(attach.getSize()<fileSize){
				if (suffix.equalsIgnoreCase("mp4")||suffix.equalsIgnoreCase("avi")||suffix.equalsIgnoreCase("rmvb")
						||suffix.equalsIgnoreCase("png")||suffix.equalsIgnoreCase("jpg")||suffix.equalsIgnoreCase("gif")) {
					fileName=System.currentTimeMillis()+rand.nextInt(1000000)+"_personal."+suffix;
					File targetFile=new File(path, fileName);
					logger.debug("create file:"+path+"/"+fileName);
					if(!targetFile.exists()){
						targetFile.mkdirs();
					}
						try {
							attach.transferTo(targetFile);
							uploadFileError="文件上传成功";
							result="success";
						} catch (IOException e) {
							e.printStackTrace();
							uploadFileError="文件写入失败";
							result="fail";
						}
					idPicPath="statics"+File.separator+"uploadfiles"+File.separator+fileName;
				}else {
					uploadFileError="上传图片格式不正确";
					result="fail";
				}
			}else {
				uploadFileError="上传文件超过预定大小";
				result="fail";
			}
		model.addAttribute("suffix",suffix);
		model.addAttribute("filePath",idPicPath);
		model.addAttribute("filename",fileName);
		model.addAttribute("uploadFileError",uploadFileError);
		model.addAttribute("result",result);
		model.addAttribute("filesize",attach.getSize()/1024/1024);
		model.addAttribute("uploadtime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		logger.debug(JSON.toJSONString(model));
		return JSON.toJSONString(model);
	}
	
	/**
	 * 公告管理模块,公告列表
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="affiche.html")
	public String affiche(Model model,HttpSession session){
		Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		model.addAllAttributes(baseModel);
		List<Affiche> afficheList=afficheService.selectAffiche(null);
		model.addAttribute("afficheList",afficheList);
		return "information/affiche";
	}
	
	@RequestMapping(value="saveAffiche.html",method=RequestMethod.POST)
	@ResponseBody
	public String saveAffiche(HttpSession session,Affiche affiche){
		Map<String, Object> model=new HashMap<String, Object>();
		logger.debug("start save affiche");
		affiche.setPublisher("admin");
		affiche.setPublishTime(new Date());
		if (afficheService.insertAffiche(affiche)>0) {
			model.put("affiche", affiche);
			model.put("saveAfficheRet", "success");
		}else {
			model.put("saveAfficheRet", "fail");
		}
		return JSON.toJSONString(model);
	}

	@RequestMapping(value="deleteAffiche.html")
	@ResponseBody
	public String deleteAffiche(@RequestParam("id") Long[] ids,Model model){
		int count=0;
		for (int i = 0; i < ids.length; i++) {
			count+=afficheService.deleteAfficheById(ids[i]);
		}
		if (ids.length==count) {
			model.addAttribute("delRet","success");
		}else {
			model.addAttribute("delRet","fail");
		}
		return JSON.toJSONString(model);
	}
	
	@RequestMapping(value="updateAffiche.html",method=RequestMethod.GET)
	public String updateAffichePage(@RequestParam("id") Long id,Model model,HttpSession session){
		Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		model.addAllAttributes(baseModel);
		Affiche affiche=afficheService.selectAfficheById(id);
		if (affiche!=null) {
			logger.debug("拿到affiche对象");
		}
		else {
			logger.debug("没有拿到affiche对象");
		}
		model.addAttribute("affiche",affiche);
		return "information/updateAffiche";
	}
	
	@RequestMapping(value="updateAffiche.html",method=RequestMethod.POST)
	@ResponseBody
	public String updateAffiche(Affiche affiche,Model model){
		affiche.setPublishTime(new Date());
		logger.debug("开始插入数据!");
		if (afficheService.updateNonEmptyAfficheById(affiche)>0) {
			model.addAttribute("updateRet","success");
			logger.debug("成功插入数据!");
		}else {
			model.addAttribute("updateRet","fail");
		}
		return JSON.toJSONString(model);
	}
	
}