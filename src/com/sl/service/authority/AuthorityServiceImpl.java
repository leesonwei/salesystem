package com.sl.service.authority;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.authority.AuthorityMapper;
import com.sl.dao.function.FunctionMapper;
import com.sl.pojo.Authority;
import com.sl.pojo.Function;
@Service
public class AuthorityServiceImpl implements AuthorityServiceAware {

	//引入AuthorityMapper
	@Resource
	private AuthorityMapper authorityMapper;
	
	//引入FunctionMapper
	@Resource
	private FunctionMapper functionMapper;
	
	/**
	 * 根据角色id获取权限列表
	 * @param authority
	 * @return
	 */
	@Override
	public List<Authority> getList(Authority authority) {		
		return authorityMapper.getList(authority);
	}

	/**
	 * 根据角色id和功能表id获取权限
	 * @param authority
	 * @return
	 */
	@Override
	public Authority getAuthority(Authority authority) {		
		return authorityMapper.getAuthority(authority);
	}

	/**
	 * 添加权限
	 * @param authority
	 * @return
	 */
	@Override
	public int addAuthority(Authority authority) {
		return authorityMapper.addAuthority(authority);
	}

	/**
	 * 修改权限
	 * @param authority
	 * @return
	 */
	@Override
	public int modifyAuthority(Authority authority) {		
		return authorityMapper.modifyAuthority(authority);
	}

	/**
	 * 删除权限
	 * @param authority
	 * @return
	 */
	@Override
	public int deleteAuthority(Authority authority) {		
		return authorityMapper.deleteAuthority(authority);
	}

	/**
	 * 添加权限的提示
	 * @param ids
	 * @param createdBy
	 * @return
	 */
	@Override
	public boolean hl_addAuthority(String[] ids, String createdBy) {
		//创建权限对象
		Authority authority = new Authority();
		//设置角色id
		authority.setRoleId(Integer.parseInt(ids[0]));
		//调用AuthorityMapper的方法
		authorityMapper.deleteAuthority(authority);
		//初始化id数组变量
		String idsSqlString="";
		//遍历数组
		for(int i=1;i<ids.length;i++){
			idsSqlString += Integer.parseInt(ids[i])+",";
		}
		//判断处理
		if(null!=idsSqlString && idsSqlString.contains(",")){
			//截取部分ids
			idsSqlString = idsSqlString.substring(0, idsSqlString.lastIndexOf(","));
			//调用FunctionMapper方法获取列表
			List<Function> funcList = functionMapper.getFunctionListByIn(idsSqlString);
			//判断处理
			if(null!=funcList && funcList.size()>0){
				//遍历列表
				for(Function function : funcList){
					authority.setFunctionId(function.getId());//获取主键id
					authority.setCreationTime(new Date());    //创建日期
					authority.setCreatedBy(createdBy);        //创建人
					//添加权限
					authorityMapper.addAuthority(authority);
				}
			}
		}
		return true;
	}

	/**
	 * 删除添加权限的提示
	 * @param authority
	 * @param checkFuncList
	 * @return
	 */
	@Override
	public boolean hl_delAddAuthority(Authority authority, String checkFuncList) {
		//初始化功能列表数组
		String[] funcList = null;
		//删除权限
		authorityMapper.deleteAuthority(authority);
		//对复选列表的判断 处理
		if(null!=checkFuncList && !checkFuncList.equals("")){
			//去掉“,”
			funcList=checkFuncList.split(",");
			//遍历数组
			for(int i=0;i<funcList.length;i++){
				authority.setFunctionId(Integer.parseInt(funcList[i]));
				//添加权限
				authorityMapper.addAuthority(authority);
			}
		}
		return true;
	}

}
