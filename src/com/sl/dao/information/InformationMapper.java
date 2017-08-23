package com.sl.dao.information;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sl.pojo.Information;

@Repository
public interface InformationMapper {

	/**
	 * 获取信息列表
	 * @param information
	 * @return
	 */
	public List<Information> getInformationList(Information information);
	
}
