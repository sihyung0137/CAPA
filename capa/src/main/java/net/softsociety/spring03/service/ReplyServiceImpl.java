package net.softsociety.spring03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.spring03.dao.ReplyDAO;
import net.softsociety.spring03.vo.Reply;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired private ReplyDAO replyDAO;

	@Override
	public int writeReply(Reply reply) {
		int result = replyDAO.insertReply(reply);
		return result;
	}

	@Override
	public List<Reply> listReply(int postnum) {
		List<Reply> list = replyDAO.listReply(postnum);
		return list;
	}

	@Override
	public int deleteReply(Reply reply) {
		int result = replyDAO.deleteReply(reply);
		return result;
	}

	@Override
	public int updateReply(Reply reply) {
		int result = replyDAO.updateReply(reply);
		return result;
	}

	@Override
	public Reply getOneReply(int replynum) {
		return replyDAO.getOneReply(replynum);
	}
}
