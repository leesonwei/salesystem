package com.sl.service.Impl.information2;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.information2.ZixunMapper;
import com.sl.pojo.Information;
import com.sl.common.Assist;
import com.sl.service.information2.ZixunService;

@Service("zixunService")
public class ZixunServiceImpl implements ZixunService{
    @Resource
	private ZixunMapper zixunMapper;
    @Override
    public long getInformationRowCount(Assist assist){
        return zixunMapper.getInformationRowCount(assist);
    }
    @Override
    public List<Information> selectInformation(Assist assist){
        return zixunMapper.selectInformation(assist);
    }
    @Override
    public Information selectInformationById(Long id){
        return zixunMapper.selectInformationById(id);
    }
    @Override
    public int insertInformation(Information value){
        return zixunMapper.insertInformation(value);
    }
    @Override
    public int insertNonEmptyInformation(Information value){
        return zixunMapper.insertNonEmptyInformation(value);
    }
    @Override
    public int deleteInformationById(Long id){
        return zixunMapper.deleteInformationById(id);
    }
    @Override
    public int deleteInformation(Assist assist){
        return zixunMapper.deleteInformation(assist);
    }
    @Override
    public int updateInformationById(Information enti){
        return zixunMapper.updateInformationById(enti);
    }
    @Override
    public int updateInformation(Information value, Assist assist){
        return zixunMapper.updateInformation(value,assist);
    }
    @Override
    public int updateNonEmptyInformationById(Information enti){
        return zixunMapper.updateNonEmptyInformationById(enti);
    }
    @Override
    public int updateNonEmptyInformation(Information value, Assist assist){
        return zixunMapper.updateNonEmptyInformation(value,assist);
    }

    public ZixunMapper getZixunMapper() {
        return this.zixunMapper;
    }

    public void setZixunMapper(ZixunMapper zixunMapper) {
        this.zixunMapper = zixunMapper;
    }

}