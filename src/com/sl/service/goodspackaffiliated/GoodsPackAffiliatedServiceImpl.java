package com.sl.service.goodspackaffiliated;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.goodspackaffiliated.GoodsPackAffiliatedMapper;
import com.sl.pojo.GoodsPackAffiliated;

@Service
public class GoodsPackAffiliatedServiceImpl implements
		GoodsPackAffiliatedServiceAware {

	@Resource
	private GoodsPackAffiliatedMapper gpaMapper;

	/**
	 * 根据商品套餐id查询套餐附属表
	 * @param goodsPackAffiliated
	 * @return
	 */
	@Override
	public List<GoodsPackAffiliated> getGoodsPackAffiliatedListById(
			GoodsPackAffiliated goodsPackAffiliated) {	
		return gpaMapper.getGoodsPackAffiliatedListById(goodsPackAffiliated);
	}

	/**
	 * 添加
	 * @param goodsPackAffiliated
	 * @return
	 */
	@Override
	public int addGoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) {		
		return gpaMapper.addGoodsPackAffiliated(goodsPackAffiliated);
	}

	/**
	 * 更新
	 * @param goodsPackAffiliated
	 * @return
	 */
	@Override
	public int modifyGoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) {		
		return gpaMapper.modifyGoodsPackAffiliated(goodsPackAffiliated);
	}

	/**
	 * 删除
	 * @param goodsPackAffiliated
	 * @return
	 */
	@Override
	public int deleteGoodPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) {		
		return gpaMapper.deleteGoodPackAffiliated(goodsPackAffiliated);
	}
	
}
