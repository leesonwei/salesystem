package com.sl.controller.elecbank;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.compiler.ast.NewExpr;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.security.x509.KeyIdentifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.sl.common.Constants;
import com.sl.common.PageSupport;
import com.sl.controller.BaseController;
import com.sl.pojo.Menu;
import com.sl.pojo.User;
import com.sl.pojo.UserAccount;
import com.sl.pojo.UserBaseAccountLog;
import com.sl.pojo.UserCash;
import com.sl.pojo.UserCoastAccountLog;
import com.sl.pojo.UserRecharge;
import com.sl.service.baseaccountlog.BaseAccountLogService;
import com.sl.service.coastaccountlog.CoastAccountLogService;
import com.sl.service.recharge.RechargeService;
import com.sl.service.user.UserServiceAware;
import com.sl.service.useraccount.UserAccountService;
import com.sl.service.usercash.UserCashService;

/**
 * 电子银行模块
 */
@Controller
public class ElecBankController extends BaseController {
    private Logger logger=Logger.getLogger(ElecBankController.class);
     @Resource
     private RechargeService rechargeService;
     @Resource
     private BaseAccountLogService baseAccountLogService;
     @Resource
     private CoastAccountLogService coastAccountLogService;
     @Resource
     private UserAccountService userAccountService;
     @Resource
     private UserServiceAware userServiceAware;
     @Resource
     private UserCashService userCashService;
    @RequestMapping("/elecbank/recharge.html")
    public String recharge(@ModelAttribute("userRecharge") UserRecharge userRecharge,
    		HttpSession session,Model model){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
    	if(baseModel!=null){   	
    		model.addAllAttributes(baseModel);
        	return "elecbank/recharge2";
    	}else{
    		return "/";
    	}    	
    }
    //充值记录
    @RequestMapping(value="/elecbank/selectbank")
    public String selectBank(UserRecharge userRecharge,Model model,HttpSession session){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
    	model.addAllAttributes(baseModel);
    	model.addAttribute("userRecharge", userRecharge);
    	return "elecbank/selectBank";
    }
    @RequestMapping("/elecbank/rechargesave")
    @ResponseBody
    public String rechargeSave(UserRecharge userRecharge,Model model){
    	userRecharge.setUser_id(String.valueOf(this.getCurrentUser().getId()));
    	userRecharge.setCurrency("RMB");
    	userRecharge.setCredited_money(userRecharge.getRecharge_money());
    	userRecharge.setCredited_time(new Date());
    	userRecharge.setAudit_user(this.getCurrentUser().getUserName());
    	userRecharge.setPV_rate(1.000);
    	userRecharge.setPV(userRecharge.getRecharge_money()*userRecharge.getPV_rate());
    	userRecharge.setBank_account(this.getCurrentUser().getUserName());
    	userRecharge.setPlatform("");
    	userRecharge.setParam("");
    	
    	Map<String,Object> map=new HashMap<String, Object>();
    	UserAccount userAccount=new UserAccount();
    	if(rechargeService.addRecharge(userRecharge)){
    		//充值金额加入到总账户表中
    		//查询出该账户中的基本账户余额
    		map.put("user_id",this.getCurrentUser().getId());
    		UserAccount userAccount1=userAccountService.getAccountUser(map);
    		if(userAccount1!=null){
    			userAccount.setUser_id(this.getCurrentUser().getId());
        		userAccount.setBase_in(userRecharge.getRecharge_money());
        		userAccount.setBase_balance(userRecharge.getRecharge_money()+userAccount1.getBase_balance());
        		if(userAccountService.update(userAccount)){
        			UserBaseAccountLog userBaseAccountLog=new UserBaseAccountLog();
        			userBaseAccountLog.setUserId(this.getCurrentUser().getId());
        			userBaseAccountLog.setActionDesc(userRecharge.getBank_name()+"充值成功");
        			userBaseAccountLog.setActionTime(new Date());
        			userBaseAccountLog.setBaseIn(userRecharge.getRecharge_money());
        			userBaseAccountLog.setBaseBalance(userAccount.getBase_balance());
        			if(baseAccountLogService.add(userBaseAccountLog)){
        				 return "success";
        			}else{
        				return "addlogerror";
        			}     			
        		}else{
        			return "failure";
        		}
    		} else{
    			return "nobaseaccount";
    		}  		   	    	  
    	}else{
    	   return "error";
    	}
    }
   
	/**
	 * 获取基本账户日志列表：分页显示
	 * @param currentpage
	 * @param queryStartTime
	 * @param queryEndTime
	 * @param session
	 * @param model
	 * @return
	 */
    @RequestMapping("/elecbank/baseAccount.html")
    public String baseAccount(Model model,HttpSession session,
    		  @RequestParam(value="queryStartTime",required=false) String queryStartTime,
    		  @RequestParam(value="queryEndTime",required=false) String queryEndTime,
    		  @RequestParam(value="currentpage",required=false)Integer currentpage){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
    	baseModel.put("queryStartTime", queryStartTime);
    	baseModel.put("queryEndTime", queryEndTime);
    	baseModel.put("userId",this.getCurrentUser().getId());
    	if(null==baseModel){//无效
    		return "redirect:/";
    	}else{
    		//处理分页
    		PageSupport pages=new PageSupport();
            //获取总记录数
    		try {
				pages.setTotalCount(baseAccountLogService.getBaseAccountLogCount(baseModel));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				pages.setTotalCount(0);
			}
    		//对总计录数判断处理
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
    			UserBaseAccountLog userBaseAccountLog=new UserBaseAccountLog();
    			userBaseAccountLog.setStarNum((pages.getPage()-1)*pages.getPageSize());
    			baseModel.put("starNum", userBaseAccountLog.getStarNum());
    			baseModel.put("pageSize", pages.getPageSize());
    		    List<UserBaseAccountLog> baseList=null;
    		    try {
					baseList=baseAccountLogService.getBaseAccountLogList(baseModel);
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
    		    pages.setItems(baseList);
    		}else{
    			pages.setItems(null);
    		}
    		model.addAllAttributes(baseModel);
        	model.addAttribute("page",pages);
        	model.addAttribute("queryStartTime", queryStartTime);
        	model.addAttribute("queryEndTime", queryEndTime);
        	return "elecbank/baseaccountlist";
    	}
    	
    }
    
	/**
	 * 获取消费账户日志列表：分页显示
	 * @param currentpage
	 * @param queryStartTime
	 * @param queryEndTime
	 * @param session
	 * @param model
	 * @return
	 */
    @RequestMapping("/elecbank/consumeAccount.html")
    public String consumeAccount(Model model,HttpSession session,
    		  @RequestParam(value="queryStartTime",required=false) String queryStartTime,
    		  @RequestParam(value="queryEndTime",required=false) String queryEndTime,
    		  @RequestParam(value="currentpage",required=false)Integer currentpage){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
    	baseModel.put("queryStartTime", queryStartTime);
    	baseModel.put("queryEndTime", queryEndTime);
    	baseModel.put("userId",this.getCurrentUser().getId());
    	if(null==baseModel){//无效
    		return "redirect:/";
    	}else{
    		//处理分页
    		PageSupport pages=new PageSupport();
            //获取总记录数
    		try {
				pages.setTotalCount(coastAccountLogService.getCoastAccountLogCount(baseModel));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				pages.setTotalCount(0);
			}
    		//对总计录数判断处理
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
    			UserCoastAccountLog userCoastAccountLog=new UserCoastAccountLog();
    			userCoastAccountLog.setStarNum((pages.getPage()-1)*pages.getPageSize());
    			baseModel.put("starNum", userCoastAccountLog.getStarNum());
    			baseModel.put("pageSize", pages.getPageSize());
    		    List<UserCoastAccountLog> baseList=null;
    		    try {
					baseList=coastAccountLogService.getCoastAccountLogList(baseModel);
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
    		    pages.setItems(baseList);
    		}else{
    			pages.setItems(null);
    		}
    		model.addAllAttributes(baseModel);
        	model.addAttribute("page",pages);
        	model.addAttribute("queryStartTime", queryStartTime);
        	model.addAttribute("queryEndTime", queryEndTime);
        	return "elecbank/consumeaccountlist";
    	}    	
    }
    @RequestMapping("/elecbank/innerTransferAccount.html")
    public String innerTransferAccount(Model model,HttpSession session){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
    	baseModel.put("user_id",this.getCurrentUser().getId());
    	UserAccount userAccount=userAccountService.getAccountUser(baseModel);
    	model.addAttribute(userAccount);
    	model.addAllAttributes(baseModel);
    	return "elecbank/innertransferaccount";
    }
    //转账时异步验证会员类型是否可以转账
    @RequestMapping("elecbank/valloginCode")
    @ResponseBody
    public String valloginCode(String loginCode){
    	User user=new User();
    	user=userServiceAware.getUserByLoginCode(loginCode);
    	if(user!=null){
    		if(user.getUserType()!="1"){
    			return "yes";
    		}else{
    			return "no";
    		}
    	}else{
    		return "noexists";
    	}
    }
    //内部转账保存
    @RequestMapping("elecbank/transgerSave")
    @ResponseBody
    public String tansgerSave(@RequestParam(value="transferMoney",required=true) Double transferMoney,
    	@RequestParam(value="userin_loginCode",required=true) String userin_loginCode,
    	@RequestParam(value="userout_id",required=true) Integer userout_id){
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("user_id", userout_id);
    	User user=new User();
    	user=userServiceAware.getUserByLoginCode(userin_loginCode);
    	UserAccount userAccount1=new UserAccount();
    	UserAccount userAccount2=new UserAccount();
    	userAccount1=userAccountService.getAccountUser(map);
    	userAccount1.setUser_id(userout_id);
    	userAccount1.setBase_balance(userAccount1.getBase_balance()-transferMoney);
    	userAccount1.setBase_out(transferMoney);
    	if(userAccountService.update(userAccount1)){
    		map.clear();
    		map.put("user_id",user.getId());
    		userAccount2=userAccountService.getAccountUser(map);
    		userAccount2.setBase_balance(userAccount2.getBase_balance()+transferMoney);
    		userAccount2.setBase_in(transferMoney);
    		if(userAccountService.update(userAccount2)){
    			//转出账户日志信息存入
    			UserBaseAccountLog userBaseAccountLog1=new UserBaseAccountLog();
    			userBaseAccountLog1.setUserId(userout_id);
    			userBaseAccountLog1.setActionDesc("转出成功");
    			userBaseAccountLog1.setActionTime(new Date());
    			userBaseAccountLog1.setBaseOut(transferMoney);
    			userBaseAccountLog1.setBaseBalance(userAccount1.getBase_balance());
    			userBaseAccountLog1.setActionType(2);
    		    baseAccountLogService.add(userBaseAccountLog1);
    		  //转入户日志信息存入
    		    UserBaseAccountLog userBaseAccountLog2=new UserBaseAccountLog();
    			userBaseAccountLog2.setUserId(user.getId());
    			userBaseAccountLog2.setActionDesc("转入成功");
    			userBaseAccountLog2.setActionTime(new Date());
    			userBaseAccountLog2.setBaseIn(transferMoney);
    			userBaseAccountLog2.setBaseBalance(userAccount2.getBase_balance());
    			userBaseAccountLog2.setActionType(2);
    		    baseAccountLogService.add(userBaseAccountLog2);
    			return "success";
    		}else{
    			return "failure";
    		}
    	}else{
    		return "errorexception";
    	}       	
    }
    //申请提现
    @RequestMapping("/elecbank/applyMoney.html")
    public String applyMoney(@ModelAttribute(value="userCash") UserCash userCash,Model model,HttpSession session){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
    	baseModel.put("user_id",this.getCurrentUser().getId());
    	UserAccount userAccount=userAccountService.getAccountUser(baseModel);
    	model.addAttribute(userAccount);
    	model.addAllAttributes(baseModel);
    	return "elecbank/usercash";
    }
    
    @RequestMapping("/elecbank/usercashsave")
    @ResponseBody
    public String userCashSave(UserCash userCash){
    	User user=this.getCurrentUser();
    	//验证银行名称，账号，账户名是否正确
    	if(userCash.getBank_account().equals(user.getBankAccount())&&userCash.getBank_name().equals(user.getBankName())&&userCash.getAccount_name().equals(user.getAccountHolder())){   	
    	userCash.setCash_time(new Date());
    	userCash.setCash_num((new Date().toString()));
    	userCash.setUser_id(this.getCurrentUser().getId().toString());
    	userCash.setCurrency("RMB");
    	userCash.setNote("申请提现");
    	userCash.setFee(0.05);
    	userCash.setTax(10.00);
    	userCash.setStat(0);
    	userCash.setCredited_money(userCash.getCash_money()*(1-userCash.getFee())-userCash.getTax());
    	userCash.setCredited_time(new Date());
    	userCash.setOperator(this.getCurrentUser().getUserName());
    	//判断基本账户余额是否大于提现金额
    	Map<String,Object> map=new HashMap<String, Object>();
    	map.put("user_id",this.getCurrentUser().getId());
    	UserAccount userAccount=userAccountService.getAccountUser(map);
    	if(userCash.getCash_money()<userAccount.getBase_balance()){//可以提现
    		if(userCashService.add(userCash)){
    			//基本账户减去提现金额
    			userAccount.setBase_balance(userAccount.getBase_balance()-userCash.getCash_money());
    			userAccount.setBase_out(userCash.getCash_money());
    			if(userAccountService.update(userAccount)){//基本账户更新余额成功后记录日志
    				UserBaseAccountLog userBaseAccountLog=new UserBaseAccountLog();
    				userBaseAccountLog.setUserId(this.getCurrentUser().getId());
    				userBaseAccountLog.setActionDesc("申请提现");
        			userBaseAccountLog.setActionTime(new Date());
        			userBaseAccountLog.setBaseOut(userCash.getCash_money());
        			userBaseAccountLog.setBaseBalance(userAccount.getBase_balance());
        			if(baseAccountLogService.add(userBaseAccountLog)){
        				return "success";
        			}else{
        				return "logfailure";
        			}
    			}else{
    				return "accountfailure";
    			}
    		}    		
    	}
    	return "cashlogfialure";
    	}else{//账户信息有误
    		return "accounterror";
    	}
    }
	/**
	 * 查询提现日志记录分页列表
	 * @param currentpage
	 * @param queryStartTime
	 * @param queryEndTime
	 * @param session
	 * @param model
	 * @return
	 */
    @RequestMapping("/elecbank/applyMoneyInfo.html")
    public String applyMoneyInfo(Model model,HttpSession session,
    		  @RequestParam(value="queryStartTime",required=false) String queryStartTime,
    		  @RequestParam(value="queryEndTime",required=false) String queryEndTime,
    		  @RequestParam(value="currentpage",required=false)Integer currentpage){
    	Map<String,Object> baseModel=(Map<String,Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
    	baseModel.put("queryStartTime", queryStartTime);
    	baseModel.put("queryEndTime", queryEndTime);
    	baseModel.put("user_id",this.getCurrentUser().getId());
    	if(null==baseModel){//无效
    		return "redirect:/";
    	}else{
    		//处理分页
    		PageSupport pages=new PageSupport();
            //获取总记录数
    		try {
				pages.setTotalCount(userCashService.getUserCashCount(baseModel));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				pages.setTotalCount(0);
			}
    		//对总计录数判断处理
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
    			UserCash userCash=new UserCash();
    			userCash.setStarNum((pages.getPage()-1)*pages.getPageSize());
    			baseModel.put("starNum", userCash.getStarNum());
    			baseModel.put("pageSize", pages.getPageSize());
    		    List<UserCash> cashList=null;
    		    try {
    		    	cashList=userCashService.getUserCashList(baseModel);
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
    		    pages.setItems(cashList);
    		}else{
    			pages.setItems(null);
    		}
    		model.addAllAttributes(baseModel);
        	model.addAttribute("page",pages);
        	model.addAttribute("queryStartTime", queryStartTime);
        	model.addAttribute("queryEndTime", queryEndTime);
        	return "elecbank/cashlist";
    	}
    	
    }

}
