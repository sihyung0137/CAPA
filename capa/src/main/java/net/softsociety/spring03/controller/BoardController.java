package net.softsociety.spring03.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.BoardService;
import net.softsociety.spring03.util.FileService;
import net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;

	@Value("${spring.servlet.multipart.location}")
	   String uploadPath;
	
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
	/**
	 * 게시글 리스트 읽기 
	 * @param boardname으로 검색 
	 */
	@GetMapping("system")
	public String system(Model model, Post post) {
		log.debug("서비스 들어오오오옴 :{}", post.getBoardname());
		
		ArrayList<Post> postlist = service.postlist(post);
		model.addAttribute("postlist", postlist);
		log.debug("postlist : {} ", postlist);
		return "boardView/system";
	}
	
	/**
	 * 게시글 작성 
	 * @param
	 */
	@GetMapping("write")
	public String write(Board board, Model model, @AuthenticationPrincipal UserDetails user) {
		log.debug("글쓰기 들어옴");
		board.setMemberid(user.getUsername());
		log.debug("글쓰기 가져온 값ㅇ : {}",board);
		
		model.addAttribute("board", board);
		
		return "boardView/write";
	}
	/**
	 * 게시글 읽기 
	 * @param  postnum으로 검색
	 */
	@GetMapping("read")
	public String read(
			Model model
			,@RequestParam(name="postnum", defaultValue = "0") int postnum
			) {
		
		Post post = service.read(postnum);
		if (post == null) {
			return "redirect:/board/system"; //글이 없으면 목록으로
		}
		
		model.addAttribute("post", post);
		return "boardView/readForm";
	}
	@GetMapping("download")
	public void fileDownload(int postnum, Model model, HttpServletResponse response) {
		//전달된 글 번호로 글 정보 조회
		Post post = service.read(postnum);
		if (post == null) return;
		
		//원래의 파일명
		String originalfile = new String(post.getOriginalfile());
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/" + post.getSavedfile();
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			//Spring의 파일 관련 유틸 이용하여 출력
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * Post 삭제
	 * @param postnum 삭제할 글 번호
	 * @param user 인증정보
	 */
	@GetMapping ("deletePost")
	public String deletePost(int postnum
			, @AuthenticationPrincipal UserDetails user) {
		
		//해당 번호의 글 정보 조회
		Post post = service.read(postnum);
		
		if (post == null) {
			return "redirect:list";
		}
		//첨부된 파일명 확인
		String savedfile = post.getSavedfile();
		
		//로그인 아이디를 post객체에 저장
		post.setMemberid(user.getUsername());
		
		//글 삭제
		int result = service.deletePost(post);
		
		if (result == 1 && savedfile != null) {
			FileService.deleteFile(uploadPath + "/" + savedfile);
		}
		
		return "redirect:system";
		
	}
}
