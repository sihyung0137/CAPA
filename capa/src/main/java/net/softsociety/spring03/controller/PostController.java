package net.softsociety.spring03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.util.FileService;
import net.softsociety.spring03.service.PostService;
import net.softsociety.spring03.vo.Post;

@Controller
@Slf4j
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService service;

	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@PostMapping("writePost")
	public String wirtePost(Post post, 
			@AuthenticationPrincipal UserDetails user,
			MultipartFile upload) {
		
		post.setMemberid(user.getUsername());
		
		log.debug("포스트 글쓰기 가져온 값 :{}",post);
		
		if (!upload.isEmpty()) {
			String savedfile = FileService.saveFile(upload, uploadPath);
			post.setOriginalfile(upload.getOriginalFilename());
			post.setSavedfile(savedfile);
		}
	
		
		int result = service.writePost(post);
		log.debug("글쓰기 성공했니ㅣㅣ?:{}", result);
		
		return "redirect:/board/boardpage";
	}
	
	
}
