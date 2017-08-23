package com.sl.service.goodsinfo;

import java.util.List;

import com.sl.pojo.GoodsInfo;

public interface GoodsInfoServiceAware {

	/**
	 * 查询商品列表 :分页显示
	 * @param goodsInfo
	 * @return
	 */
	public List<GoodsInfo> getGoodsInfoList(GoodsInfo goodsInfo);
	
	/**
	 * 计算总记录数：用于分页
	 * @param goodsInfo
	 * @return
	 */
	public int count(GoodsInfo goodsInfo);
	
	/**
	 * 查询商品编码是否存在
	 * @param goodsInfo
	 * @return
	 */
	public int goodsSNIsExit(GoodsInfo goodsInfo);
	
	/**
	 * 根据id主键查询商品
	 * @param goodsInfo
	 * @return
	 */
	public GoodsInfo getGoodsInfoById(GoodsInfo goodsInfo);
	
	/**
	 * 在商品套餐附属表中查询是否存在商品
	 * @param goodsInfo
	 * @return
	 */
	public int isExitInPack(GoodsInfo goodsInfo);
	
	/**
	 * 添加商品
	 * @param goodsInfo
	 * @return
	 */
	public int addGoodsInfo(GoodsInfo goodsInfo);
	
	/**
	 * 更新商品
	 * @param goodsInfo
	 * @return
	 */
	public int modifyGoodsInfo(GoodsInfo goodsInfo);
	
	/**
	 * 删除商品
	 * @param goodsInfo
	 * @return
	 */
	public int deleteGoodsInfo(GoodsInfo goodsInfo);
	
}
