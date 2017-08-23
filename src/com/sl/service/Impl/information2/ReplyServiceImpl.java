package com.sl.service.Impl.information2;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.information2.ReplyMapper;
import com.sl.pojo.Reply;
import com.sl.common.Assist;
import com.sl.service.information2.ReplyService;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{
    @Resource
	private ReplyMapper replyMapper;
    @Override
    public long getReplyRowCount(Assist assist){
        return replyMapper.getReplyRowCount(assist);
    }
    @Override
    public List<Reply> selectReply(Assist assist){
        return replyMapper.selectReply(assist);
    }
    @Override
    public Reply selectReplyById(Long id){
        return replyMapper.selectReplyById(id);
    }
    @Override
    public int insertReply(Reply value){
        return replyMapper.insertReply(value);
    }
    @Override
    public int insertNonEmptyReply(Reply value){
        return replyMapper.insertNonEmptyReply(value);
    }
    @Override
    public int deleteReplyById(Long id){
        return replyMapper.deleteReplyById(id);
    }
    @Override
    public int deleteReply(Assist assist){
        return replyMapper.deleteReply(assist);
    }
    @Override
    public int updateReplyById(Reply enti){
        return replyMapper.updateReplyById(enti);
    }
    @Override
    public int updateReply(Reply value, Assist assist){
        return replyMapper.updateReply(value,assist);
    }
    @Override
    public int updateNonEmptyReplyById(Reply enti){
        return replyMapper.updateNonEmptyReplyById(enti);
    }
    @Override
    public int updateNonEmptyReply(Reply value, Assist assist){
        return replyMapper.updateNonEmptyReply(value,assist);
    }

    public ReplyMapper getReplyMapper() {
        return this.replyMapper;
    }

    public void setReplyMapper(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

}