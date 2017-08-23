package com.sl.service.goodsinfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.goodsinfo.GoodsInfoMapper;
import com.sl.pojo.GoodsInfo;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoServiceAware {

	@Resource
	private GoodsInfoMapper goodsInfoMapper;

	/**
	 * 查询商品列表 :分页显示
	 * @param goodsInfo
	 * @return
	 */
	@Override
	public List<GoodsInfo> getGoodsInfoList(GoodsInfo goodsInfo) {		
		return goodsInfoMapper.getGoodsInfoList(goodsInfo);
	}

	/**
	 * 计算总记录数：用于分页
	 * @param goodsInfo
	 * @return
	 */
	@Override
	public int count(GoodsInfo goodsInfo) {		
		return goodsInfoMapper.count(goodsInfo);
	}

	/**
	 * 查询商品编码是否存在
	 * @param goodsInfo
	 * @return
	 */
	@Override
	public int goodsSNIsExit(GoodsInfo goodsInfo) {		
		return goodsInfoMapper.goodsSNIsExit(goodsInfo);
	}

	/**
	 * 根据id主键查询商品
	 * @param goodsInfo
	 * @return
	 */
	@Override
	public GoodsInfo getGoodsInfoById(GoodsInfo goodsInfo) {		
		return goodsInfoMapper.getGoodsInfoById(goodsInfo);
	}

	/**
	 * 在商品套餐附属表中查询是否存在商品
	 * @param goodsInfo
	 * @return
	 */
	@Override
	public int isExitInPack(GoodsInfo goodsInfo) {		
		return goodsInfoMapper.isExitInPack(goodsInfo);
	}

	/**
	 * 添加商品
	 * @param goodsInfo
	 * @return
	 */
	@Override
	public int addGoodsInfo(GoodsInfo goodsInfo) {		
		return goodsInfoMapper.addGoodsInfo(goodsInfo);
	}

	/**
	 * 更新商品
	 * @param goodsInfo
	 * @return
	 */
	@Override
	public int modifyGoodsInfo(GoodsInfo goodsInfo) {		
		return goodsInfoMapper.modifyGoodsInfo(goodsInfo);
	}

	/**
	 * 删除商品
	 * @param goodsInfo
	 * @return
	 */
	@Override
	public int deleteGoodsInfo(GoodsInfo goodsInfo) {		
		return goodsInfoMapper.deleteGoodsInfo(goodsInfo);
	}
	
}
