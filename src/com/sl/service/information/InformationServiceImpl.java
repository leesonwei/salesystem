package com.sl.service.information;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.information.InformationMapper;
import com.sl.pojo.Information;
@Service
public class InformationServiceImpl implements InformationServiceAware{

	@Resource
	private InformationMapper informationMapper;
	
	/**
	 * 获取信息列表
	 * @param information
	 * @return
	 */
	@Override
	public List<Information> getInformationList(Information information) {
		
		return informationMapper.getInformationList(information);
	}

}
