package com.sl.service.goodspack;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.goodspack.GoodsPackMapper;
import com.sl.dao.goodspackaffiliated.GoodsPackAffiliatedMapper;
import com.sl.pojo.GoodsPack;
import com.sl.pojo.GoodsPackAffiliated;

@Service
public class GoodsPackServiceImpl implements GoodsPackServiceAware {

	@Resource
	private GoodsPackMapper gpMapper;
	
	@Resource
	private GoodsPackAffiliatedMapper gpaMapper;

	/**
	 * 查询套餐列表：分页显示
	 * @param goodsPack
	 * @return
	 */
	@Override
	public List<GoodsPack> getGoodsPackList(GoodsPack goodsPack) {		
		return gpMapper.getGoodsPackList(goodsPack);
	}

	/**
	 * 查询总记录数：分页显示
	 * @param goodsPack
	 * @return
	 */
	@Override
	public int count(GoodsPack goodsPack) {		
		return gpMapper.count(goodsPack);
	}

	/**
	 * 根据套餐编码或id查询套餐编码是否存在
	 * @param goodsPack
	 * @return
	 */
	@Override
	public int goodsPackCodeIsExit(GoodsPack goodsPack) {		
		return gpMapper.goodsPackCodeIsExit(goodsPack);
	}

	/**
	 * 通过主键id查询商品套餐 
	 * @param goodsPack
	 * @return
	 */
	@Override
	public GoodsPack getGoodsPackById(GoodsPack goodsPack) {		
		return gpMapper.getGoodsPackById(goodsPack);
	}
	
	/**
	 *  获取添加套餐表的最后插入的id值 
	 * @return
	 */
	@Override
	public int getAddGoodsPackId() {
		return gpMapper.getAddGoodsPackId();
	}

	/**
	 * 添加商品套餐
	 * @param goodsPack
	 * @return
	 */
	@Override
	public int addGoodsPack(GoodsPack goodsPack) {		
		return gpMapper.addGoodsPack(goodsPack);
	}

	/**
	 * 更新商品套餐
	 * @param goodsPack
	 * @return
	 */
	@Override
	public int modifyGoodsPack(GoodsPack goodsPack) {		
		return gpMapper.modifyGoodsPack(goodsPack);
	}

	/**
	 * 删除商品套餐
	 * @param goodsPack
	 * @return
	 */
	@Override
	public int deleteGoodsPack(GoodsPack goodsPack) {		
		return gpMapper.deleteGoodsPack(goodsPack);
	}

	/**
	 * 添加到套餐附属表
	 * @param goodsPack
	 * @param apaList
	 * @return
	 */
	@Override
	public boolean hl_addGoodsPack(GoodsPack goodsPack,
			List<GoodsPackAffiliated> apaList) {
		//初始化添加附属表的id变量
		int addGoodsPackId=0;
		//调用GoodsPackMapper的方法,添加商品套餐
		gpMapper.addGoodsPack(goodsPack);
		//获取添加套餐的id值
		addGoodsPackId=gpMapper.getAddGoodsPackId();
		//判断处理
		if(null != apaList && apaList.size() > 0 && addGoodsPackId != 0){
			for(int i = 0; i <  apaList.size(); i++){
				if(null != apaList.get(i)){
					apaList.get(i).setGoodsPackId(addGoodsPackId);
					gpaMapper.addGoodsPackAffiliated(apaList.get(i));
				}
			}
		}
		return true;
	}

	/**
	 * 更新到商品套餐附属表
	 * @param goodsPack
	 * @param apaList
	 * @return
	 */
	@Override
	public boolean hl_modifyGoodsPack(GoodsPack goodsPack,
			List<GoodsPackAffiliated> apaList) {
		//对商品套餐进行了修改
		gpMapper.modifyGoodsPack(goodsPack);
		//获取商品套餐的id
		int goodsPackId = goodsPack.getId();
		//实例化附属表对象
		GoodsPackAffiliated gpa = new GoodsPackAffiliated();
		//保存id值
		gpa.setGoodsPackId(goodsPackId);
		//删除商品套餐
		gpaMapper.deleteGoodPackAffiliated(gpa);
		//判断处理
		if(null != apaList){
			for(int i = 0; i < apaList.size(); i++){
				if(null != apaList.get(i)){
					apaList.get(i).setGoodsPackId(goodsPackId);
					gpaMapper.addGoodsPackAffiliated(apaList.get(i));
				}
			}
		}
		return true;
	}

	
	
}
