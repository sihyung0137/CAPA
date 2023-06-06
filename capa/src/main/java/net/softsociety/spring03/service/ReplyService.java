package net.softsociety.spring03.service;

import java.util.List;

import net.softsociety.spring03.vo.Reply;

public interface ReplyService {

	int writeReply(Reply reply);

	List<Reply> listReply(int postnum);

	int deleteReply(Reply reply);

	int updateReply(Reply reply);

	Reply getOneReply(int replynum);

}
