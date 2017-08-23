package com.sl.service.goodspack;

import java.util.List;

import com.sl.pojo.GoodsPack;
import com.sl.pojo.GoodsPackAffiliated;

public interface GoodsPackServiceAware {

	/**
	 * 查询套餐列表：分页显示
	 * @param goodsPack
	 * @return
	 */
	public List<GoodsPack> getGoodsPackList(GoodsPack goodsPack);
	
	/**
	 * 查询总记录数：分页显示
	 * @param goodsPack
	 * @return
	 */
	public int count(GoodsPack goodsPack);
	
	/**
	 * 根据套餐编码或id查询套餐编码是否存在
	 * @param goodsPack
	 * @return
	 */
	public int goodsPackCodeIsExit(GoodsPack goodsPack);
	
	/**
	 * 通过主键id查询商品套餐 
	 * @param goodsPack
	 * @return
	 */
	public GoodsPack getGoodsPackById(GoodsPack goodsPack);
	
	/**
	 *  获取添加套餐表的最后插入的id值 
	 * @return
	 */
	public int getAddGoodsPackId();
	
	/**
	 * 添加商品套餐
	 * @param goodsPack
	 * @return
	 */
	public int addGoodsPack(GoodsPack goodsPack);
	
	/**
	 * 更新商品套餐
	 * @param goodsPack
	 * @return
	 */
	public int modifyGoodsPack(GoodsPack goodsPack);
	
	/**
	 * 删除商品套餐
	 * @param goodsPack
	 * @return
	 */
	public int deleteGoodsPack(GoodsPack goodsPack);
	
	/**
	 * 添加到套餐附属表
	 * @param goodsPack
	 * @param apaList
	 * @return
	 */
	public boolean hl_addGoodsPack(GoodsPack goodsPack,List<GoodsPackAffiliated> apaList);
	
	/**
	 * 更新到商品套餐附属表
	 * @param goodsPack
	 * @param apaList
	 * @return
	 */
	public boolean hl_modifyGoodsPack(GoodsPack goodsPack,List<GoodsPackAffiliated> apaList);
	
}
