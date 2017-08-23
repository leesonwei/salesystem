package com.sl.service.affiche;

import java.util.List;

import com.sl.pojo.Affiche;

public interface AfficheServiceAware {

	/**
	 * 获取入口公告列表
	 * @param affiche
	 * @return
	 */
	public List<Affiche> getPortalAfficheList(Affiche affiche);
	
}
