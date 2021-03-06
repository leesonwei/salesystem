package com.sl.service.goodspackaffiliated;

import java.util.List;

import com.sl.pojo.GoodsPackAffiliated;

public interface GoodsPackAffiliatedServiceAware {

	/**
	 * 根据商品套餐id查询套餐附属表
	 * @param goodsPackAffiliated
	 * @return
	 */
	public List<GoodsPackAffiliated> getGoodsPackAffiliatedListById(GoodsPackAffiliated goodsPackAffiliated);
	
	/**
	 * 添加
	 * @param goodsPackAffiliated
	 * @return
	 */
	public int addGoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated);
	
	/**
	 * 更新
	 * @param goodsPackAffiliated
	 * @return
	 */
	public int modifyGoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated);
	
	/**
	 * 删除
	 * @param goodsPackAffiliated
	 * @return
	 */
	public int deleteGoodPackAffiliated(GoodsPackAffiliated goodsPackAffiliated);
	
	
}
