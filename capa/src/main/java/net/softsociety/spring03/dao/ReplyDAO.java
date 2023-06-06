package net.softsociety.spring03.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring03.vo.Reply;

@Mapper
public interface ReplyDAO {

	int insertReply(Reply reply);

	List<Reply> listReply(int postnum);

	int deleteReply(Reply reply);

	int updateReply(Reply reply);

	Reply getOneReply(int replynum);

}
