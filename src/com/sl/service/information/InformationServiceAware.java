package com.sl.service.information;

import java.util.List;

import com.sl.pojo.Information;

public interface InformationServiceAware {

	/**
	 * 获取信息列表
	 * @param information
	 * @return
	 */
	public List<Information> getInformationList(Information information);
	
}
