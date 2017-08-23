package com.sl.service.information2;
import java.util.List;
import com.sl.pojo.Affiche;
import com.sl.common.Assist;
public interface GonggaoService{
	
	/**
	 * 获得公告数量
	 * @param assist 参考Assist类说明
	 * @return 公告数量
	 */
    long getAfficheRowCount(Assist assist);
    
    /**
     * 获得公告列表
     * @param assist 参考Assist类说明
     * @return 公告列表
     */
    List<Affiche> selectAffiche(Assist assist);
    
    /**
     * 获得指定公告
     * @param id 公告id
     * @return 公告对象
     */
    Affiche selectAfficheById(Long id);
    
    /**
     * 插入没有空值的公告
     * @param value 没有空值的公告
     * @return 成功返回1失败返回0
     */
    int insertAffiche(Affiche value);
    
    /**
     * 插入有空值的公告
     * @param value 有空值的公告
     * @return 成功返回1失败返回0
     */
    int insertNonEmptyAffiche(Affiche value);
    
    /**
     * 删除指定公告
     * @param id 公告id
     * @return 成功返回1失败返回0
     */
    int deleteAfficheById(Long id);
    
    /**
     * 删除指定公告
     * @param assist 参考Assist说明
     * @return 成功返回1失败返回0
     */
    int deleteAffiche(Assist assist);
    
    /**
     * 更新指定公告
     * @param enti 
     * @return 成功返回1失败返回0
     */
    int updateAfficheById(Affiche enti);
    int updateAffiche(Affiche value, Assist assist);
    int updateNonEmptyAfficheById(Affiche enti);
    int updateNonEmptyAffiche(Affiche value, Assist assist);
}