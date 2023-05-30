package net.softsociety.spring03.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.BoardService;
import net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;

	@GetMapping("boardpage")
	public String boardpage() {
		return"boardView/boardpage";
	}
	
	@GetMapping("createBoard")
	public String createBoard() {
		log.debug("게시판 생성 들어왔니????????????????????");
		return"boardView/createBoard";
	}
	
	@PostMapping("createBoard")
	public String createBoard2(Board board) {
		log.debug("게시판 생성 가지고 온 값 : {}", board);
		
		int result = service.createBoard(board);
		log.debug("게시판 생성 성공?:{}", result);
		
		return "redirect:boardpage";
	}
	
	@GetMapping("system")
	public String system(String boardname, Model model, Post post) {
		log.debug("서비스 들어오오오옴 :{}", boardname);
		
		ArrayList<Post> postlist = service.postlist(post);
		model.addAttribute("postlist", postlist);
		log.debug("postlist : {} ", postlist);
		return "boardView/system";
	}
	
	@GetMapping("write")
	public String write(Board board, Model model, @AuthenticationPrincipal UserDetails user) {
		log.debug("글쓰기 들어옴");
		board.setMemberid(user.getUsername());
		log.debug("글쓰기 가져온 값ㅇ : {}",board);
		
		model.addAttribute("board", board);
		
		return "boardView/write";
	}
	
}
