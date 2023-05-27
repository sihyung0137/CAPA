package net.softsociety.spring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/post")
public class PostController {

	@PostMapping("writePost")
	public String wirtePost() {
		log.debug("포스트 글쓰기 들어옴");
		return "redirect:/board/boardpage";
	}
}
