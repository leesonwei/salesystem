package com.sl.dao.affiche;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sl.pojo.Affiche;
@Repository
public interface AfficheMapper {

	/**
	 * 获取入口公告列表
	 * @param affiche
	 * @return
	 */
	public List<Affiche> getPortalAfficheList(Affiche affiche);
	
	
	
	
}
