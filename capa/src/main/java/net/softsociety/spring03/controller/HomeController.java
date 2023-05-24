package net.softsociety.spring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping({"", "/"})
	public String home() {
		log.debug("homepage 접속");
		return "home";
	}
}
