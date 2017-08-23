package com.sl.service.information2;
import java.util.List;
import com.sl.pojo.Reply;
import com.sl.common.Assist;
public interface ReplyService{
    long getReplyRowCount(Assist assist);
    List<Reply> selectReply(Assist assist);
    Reply selectReplyById(Long id);
    int insertReply(Reply value);
    int insertNonEmptyReply(Reply value);
    int deleteReplyById(Long id);
    int deleteReply(Assist assist);
    int updateReplyById(Reply enti);
    int updateReply(Reply value, Assist assist);
    int updateNonEmptyReplyById(Reply enti);
    int updateNonEmptyReply(Reply value, Assist assist);
}