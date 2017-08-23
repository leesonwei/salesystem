package com.sl.dao.information2;
import com.sl.pojo.Reply;
import java.util.List;
import com.sl.common.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyMapper{
    long getReplyRowCount(Assist assist);
    List<Reply> selectReply(Assist assist);
    Reply selectReplyById(Long id);
    int insertReply(Reply value);
    int insertNonEmptyReply(Reply value);
    int deleteReplyById(Long id);
    int deleteReply(Assist assist);
    int updateReplyById(Reply enti);
    int updateReply(@Param("enti") Reply value, @Param("assist") Assist assist);
    int updateNonEmptyReplyById(Reply enti);
    int updateNonEmptyReply(@Param("enti") Reply value, @Param("assist") Assist assist);
}