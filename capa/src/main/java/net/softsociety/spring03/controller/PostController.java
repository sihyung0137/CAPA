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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.util.FileService;
import net.softsociety.spring03.service.BoardService;
import net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.service.PostService;
import net.softsociety.spring03.vo.Post;

@Controller
@Slf4j
@RequestMapping("/post")
public class PostController {
	
	
	 @Autowired private PostService service;
	
	 @Value("${spring.servlet.multipart.location}") String uploadPath;
	 
	 @PostMapping("writePost") 
	 
	 public String writePost(Post post, @AuthenticationPrincipal UserDetails user, MultipartFile upload) {
	 
		 post.setMemberid(user.getUsername());
		 
		 log.debug("포스트 글쓰기 가져온 값 :{}",post);
		 
		 if (!upload.isEmpty()) { 
			 String savedfile = FileService.saveFile(upload,uploadPath); 
			 post.setOriginalfile(upload.getOriginalFilename());
			 post.setSavedfile(savedfile); 
		 }
		 
		 
		 int result = service.writePost(post); 
		 log.debug("글쓰기 성공했니ㅣㅣ?:{}", result);
	 
	 return "redirect:/board/boardpage"; }
	

	
}
