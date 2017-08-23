package com.sl.service.Impl.information2;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.information2.GonggaoMapper;
import com.sl.pojo.Affiche;
import com.sl.common.Assist;
import com.sl.service.information2.GonggaoService;

@Service("gonggaoService")
public class GonggaoServiceImpl implements GonggaoService{
    @Resource
	private GonggaoMapper gonggaoMapper;
    @Override
    public long getAfficheRowCount(Assist assist){
        return gonggaoMapper.getAfficheRowCount(assist);
    }
    @Override
    public List<Affiche> selectAffiche(Assist assist){
        return gonggaoMapper.selectAffiche(assist);
    }
    @Override
    public Affiche selectAfficheById(Long id){
        return gonggaoMapper.selectAfficheById(id);
    }
    @Override
    public int insertAffiche(Affiche value){
        return gonggaoMapper.insertAffiche(value);
    }
    @Override
    public int insertNonEmptyAffiche(Affiche value){
        return gonggaoMapper.insertNonEmptyAffiche(value);
    }
    @Override
    public int deleteAfficheById(Long id){
        return gonggaoMapper.deleteAfficheById(id);
    }
    @Override
    public int deleteAffiche(Assist assist){
        return gonggaoMapper.deleteAffiche(assist);
    }
    @Override
    public int updateAfficheById(Affiche enti){
        return gonggaoMapper.updateAfficheById(enti);
    }
    @Override
    public int updateAffiche(Affiche value, Assist assist){
        return gonggaoMapper.updateAffiche(value,assist);
    }
    @Override
    public int updateNonEmptyAfficheById(Affiche enti){
        return gonggaoMapper.updateNonEmptyAfficheById(enti);
    }
    @Override
    public int updateNonEmptyAffiche(Affiche value, Assist assist){
        return gonggaoMapper.updateNonEmptyAffiche(value,assist);
    }

    public GonggaoMapper getGonggaoMapper() {
        return this.gonggaoMapper;
    }

    public void setGonggaoMapper(GonggaoMapper gonggaoMapper) {
        this.gonggaoMapper = gonggaoMapper;
    }

}