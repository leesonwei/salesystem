package com.sl.pojo;

/**
 * 数据表goods_pack_affiliated
 **/
public class GoodsPackAffiliated extends Base{
	
	/**
	 * 成员变量
	 * */
	private Integer id;            //主键id
	private Integer goodsPackId;   //商品套餐主表id
	private Integer goodsInfoId;   //商品信息表id
	private Integer goodsNum;      //商品数量
	
	private String goodsName;      //商品名称
	private Double realPrice;      //商品优惠价格
	private String unit;           //商品单位
	
	/**
	 * 封装方法
	 * */
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoodsPackId() {
		return goodsPackId;
	}
	public void setGoodsPackId(Integer goodsPackId) {
		this.goodsPackId = goodsPackId;
	}
	public Integer getGoodsInfoId() {
		return goodsInfoId;
	}
	public void setGoodsInfoId(Integer goodsInfoId) {
		this.goodsInfoId = goodsInfoId;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
}
