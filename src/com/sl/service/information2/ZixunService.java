package com.sl.service.information2;
import java.util.List;
import com.sl.pojo.Information;
import com.sl.common.Assist;
public interface ZixunService{
    long getInformationRowCount(Assist assist);
    List<Information> selectInformation(Assist assist);
    Information selectInformationById(Long id);
    int insertInformation(Information value);
    int insertNonEmptyInformation(Information value);
    int deleteInformationById(Long id);
    int deleteInformation(Assist assist);
    int updateInformationById(Information enti);
    int updateInformation(Information value, Assist assist);
    int updateNonEmptyInformationById(Information enti);
    int updateNonEmptyInformation(Information value, Assist assist);
}