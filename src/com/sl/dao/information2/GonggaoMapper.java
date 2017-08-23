package com.sl.dao.information2;
import com.sl.pojo.Affiche;
import java.util.List;
import com.sl.common.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GonggaoMapper{
    long getAfficheRowCount(Assist assist);
    List<Affiche> selectAffiche(Assist assist);
    Affiche selectAfficheById(Long id);
    int insertAffiche(Affiche value);
    int insertNonEmptyAffiche(Affiche value);
    int deleteAfficheById(Long id);
    int deleteAffiche(Assist assist);
    int updateAfficheById(Affiche enti);
    int updateAffiche(@Param("enti") Affiche value, @Param("assist") Assist assist);
    int updateNonEmptyAfficheById(Affiche enti);
    int updateNonEmptyAffiche(@Param("enti") Affiche value, @Param("assist") Assist assist);
}