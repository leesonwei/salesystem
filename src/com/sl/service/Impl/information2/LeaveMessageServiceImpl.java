package com.sl.service.Impl.information2;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.information2.LeaveMessageMapper;
import com.sl.pojo.LeaveMessage;
import com.sl.common.Assist;
import com.sl.service.information2.LeaveMessageService;

@Service("leaveMessageService")
public class LeaveMessageServiceImpl implements LeaveMessageService{
    @Resource
	private LeaveMessageMapper leaveMessageMapper;
    @Override
    public long getLeaveMessageRowCount(Assist assist){
        return leaveMessageMapper.getLeaveMessageRowCount(assist);
    }
    @Override
    public List<LeaveMessage> selectLeaveMessage(Assist assist){
        return leaveMessageMapper.selectLeaveMessage(assist);
    }
    @Override
    public LeaveMessage selectLeaveMessageById(Long id){
        return leaveMessageMapper.selectLeaveMessageById(id);
    }
    @Override
    public int insertLeaveMessage(LeaveMessage value){
        return leaveMessageMapper.insertLeaveMessage(value);
    }
    @Override
    public int insertNonEmptyLeaveMessage(LeaveMessage value){
        return leaveMessageMapper.insertNonEmptyLeaveMessage(value);
    }
    @Override
    public int deleteLeaveMessageById(Long id){
        return leaveMessageMapper.deleteLeaveMessageById(id);
    }
    @Override
    public int deleteLeaveMessage(Assist assist){
        return leaveMessageMapper.deleteLeaveMessage(assist);
    }
    @Override
    public int updateLeaveMessageById(LeaveMessage enti){
        return leaveMessageMapper.updateLeaveMessageById(enti);
    }
    @Override
    public int updateLeaveMessage(LeaveMessage value, Assist assist){
        return leaveMessageMapper.updateLeaveMessage(value,assist);
    }
    @Override
    public int updateNonEmptyLeaveMessageById(LeaveMessage enti){
        return leaveMessageMapper.updateNonEmptyLeaveMessageById(enti);
    }
    @Override
    public int updateNonEmptyLeaveMessage(LeaveMessage value, Assist assist){
        return leaveMessageMapper.updateNonEmptyLeaveMessage(value,assist);
    }

    public LeaveMessageMapper getLeaveMessageMapper() {
        return this.leaveMessageMapper;
    }

    public void setLeaveMessageMapper(LeaveMessageMapper leaveMessageMapper) {
        this.leaveMessageMapper = leaveMessageMapper;
    }

}