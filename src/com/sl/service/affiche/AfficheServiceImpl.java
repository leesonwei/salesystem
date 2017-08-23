package com.sl.service.affiche;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.affiche.AfficheMapper;
import com.sl.pojo.Affiche;

@Service
public class AfficheServiceImpl implements AfficheServiceAware {

	@Resource
	private AfficheMapper afficheMapper;
	
	/**
	 * 获取入口公告列表
	 * @param affiche
	 * @return
	 */
	@Override
	public List<Affiche> getPortalAfficheList(Affiche affiche) {
		
		return afficheMapper.getPortalAfficheList(affiche);
	}

	
}
