package com.sl.service.information2;
import java.util.List;
import com.sl.pojo.LeaveMessage;
import com.sl.common.Assist;
public interface LeaveMessageService{
    long getLeaveMessageRowCount(Assist assist);
    List<LeaveMessage> selectLeaveMessage(Assist assist);
    LeaveMessage selectLeaveMessageById(Long id);
    int insertLeaveMessage(LeaveMessage value);
    int insertNonEmptyLeaveMessage(LeaveMessage value);
    int deleteLeaveMessageById(Long id);
    int deleteLeaveMessage(Assist assist);
    int updateLeaveMessageById(LeaveMessage enti);
    int updateLeaveMessage(LeaveMessage value, Assist assist);
    int updateNonEmptyLeaveMessageById(LeaveMessage enti);
    int updateNonEmptyLeaveMessage(LeaveMessage value, Assist assist);
}