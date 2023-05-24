package net.softsociety.spring03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.MemberService;
import net.softsociety.spring03.vo.Member;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("/join")
	public String join() {
		log.debug("로그인화면접속");
		return "member/joinForm";
	}
	
	@PostMapping("/join")
	public String join2(Member m) {
		log.debug("param : {}", m);
		
		int result = service.joinInsert(m);
		log.debug("회원가입 성공 result: {}, m : {}", result, m);
		return "redirect:/";
	}
	
	@GetMapping("idcheck")
	public String idChech() {
		return "/member/idCheck";
	}
	
	@PostMapping("idcheck")
	public String idcheck2(String searchId, Model model) {
		log.debug("param: {}", searchId);
		boolean result = service.selectId(searchId);
		// 아이디 값이 존재하면 false 존재하지 않으면 true
		log.debug("result: {}", result);
		
		model.addAttribute("searchId", searchId);
		model.addAttribute("result", result);
		
		return "/member/idCheck";
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@GetMapping("mypage")
	public String mypage(@AuthenticationPrincipal UserDetails user, Model model) {
		log.debug("user: {}", user);
		
		// Member m = service.selectOne(user.getUsername());
		
		// model.addAttribute("member", m);
		
		return "member/mypage";
	}	
	
	@PostMapping("mypage")
	public String mypage2(@AuthenticationPrincipal UserDetails user, Member m) {
		log.debug("수정할 정보 m: {}", m);
		
		// int result = service.updateMember(m);
		
		return "redirect:/";
	}
}
