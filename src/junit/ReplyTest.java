package junit;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sl.common.Assist;
import com.sl.pojo.LeaveMessage;
import com.sl.pojo.Reply;
import com.sl.service.Impl.information2.ReplyServiceImpl;
import com.sl.service.information2.LeaveMessageService;
import com.sl.service.information2.ReplyService;

public class ReplyTest {

	@Test
	public void test() {
		ApplicationContext atxContext=new ClassPathXmlApplicationContext("applicationContext-mybatis.xml"); 
		ReplyService replyServiceImpl=atxContext.getBean("replyService",ReplyService.class);
		LeaveMessageService leaveMessageService=atxContext.getBean("leaveMessageService",LeaveMessageService.class);
		List<LeaveMessage> messageList=leaveMessageService.selectLeaveMessage(null);
		List<Reply> replys=null;
		Assist assist=new Assist();
		for (LeaveMessage leaveMessage : messageList) {
			assist.setRequires(Assist.and_eq("messageId", String.valueOf((leaveMessage.getId()))));
			replys=replyServiceImpl.selectReply(assist);
			leaveMessage.setReplys(replys);
			for (Reply reply : leaveMessage.getReplys()) {
				System.out.println(reply.getReplycontent());
			}
		}
	}

}
