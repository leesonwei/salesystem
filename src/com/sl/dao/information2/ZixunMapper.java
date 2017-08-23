package com.sl.dao.information2;
import com.sl.pojo.Information;
import java.util.List;
import com.sl.common.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZixunMapper{
    long getInformationRowCount(Assist assist);
    List<Information> selectInformation(Assist assist);
    Information selectInformationById(Long id);
    int insertInformation(Information value);
    int insertNonEmptyInformation(Information value);
    int deleteInformationById(Long id);
    int deleteInformation(Assist assist);
    int updateInformationById(Information enti);
    int updateInformation(@Param("enti") Information value, @Param("assist") Assist assist);
    int updateNonEmptyInformationById(Information enti);
    int updateNonEmptyInformation(@Param("enti") Information value, @Param("assist") Assist assist);
}