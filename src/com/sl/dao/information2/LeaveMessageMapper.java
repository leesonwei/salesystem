package com.sl.dao.information2;
import com.sl.pojo.LeaveMessage;
import java.util.List;
import com.sl.common.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveMessageMapper{
    long getLeaveMessageRowCount(Assist assist);
    List<LeaveMessage> selectLeaveMessage(Assist assist);
    LeaveMessage selectLeaveMessageById(Long id);
    int insertLeaveMessage(LeaveMessage value);
    int insertNonEmptyLeaveMessage(LeaveMessage value);
    int deleteLeaveMessageById(Long id);
    int deleteLeaveMessage(Assist assist);
    int updateLeaveMessageById(LeaveMessage enti);
    int updateLeaveMessage(@Param("enti") LeaveMessage value, @Param("assist") Assist assist);
    int updateNonEmptyLeaveMessageById(LeaveMessage enti);
    int updateNonEmptyLeaveMessage(@Param("enti") LeaveMessage value, @Param("assist") Assist assist);
}