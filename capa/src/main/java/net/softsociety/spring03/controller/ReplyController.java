package net.softsociety.spring03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.ReplyService;
import net.softsociety.spring03.vo.Reply;

@Controller
@Slf4j
@RequestMapping("reply")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	/**
	 * 댓글 저장
	 * @param reply 저장할 리플 정보
	 */
	@PostMapping("writeReply")
	@ResponseBody // 비동기 처리방식
	public String writeReply(
			Reply reply
			, @AuthenticationPrincipal UserDetails user) {
		
		reply.setMemberid(user.getUsername());
		
		log.debug("저장할 댓글 정보", reply);
		int result = service.writeReply(reply);
		
		return "ok";
	}
	/**
	 * 댓글 리스트
	 * @param postnum 글 번호
	 */
	@GetMapping("loadReply")
	@ResponseBody
	public List<Reply> loadReply(int postnum) {
		List<Reply> replyList = service.listReply(postnum);
		
		return replyList;
	}
	/**
	 * 댓글 삭제
	 * @param reply 삭제할 리플 정보
	 */
	@GetMapping("deleteReply")
	@ResponseBody
	public String repllyWrite(
			Reply reply
			, @AuthenticationPrincipal UserDetails user) {
		
		reply.setMemberid(user.getUsername());
		int result = service.deleteReply(reply);
		
		return "redirect:/board/read?boardnum="  + reply.getPostnum();
	}
	/**
	 * 리플 한개 정보 가져오기
	 * @param replynum 댓글 번호
	 */
	@GetMapping("getOneReply")
	@ResponseBody
	public Reply getOneReply(int replynum) {
		log.debug("replynum : {}", replynum);
		return service.getOneReply(replynum);
	}
	/**
	 * 댓글 수정
	 * @param reply, user
	 */
	@PostMapping("updateReply")
	@ResponseBody
	public String updateReply(Reply reply
			, @AuthenticationPrincipal UserDetails user) {
		reply.setMemberid(user.getUsername());
		
		int result = service.updateReply(reply);
		
		return "ok";
	}
}
