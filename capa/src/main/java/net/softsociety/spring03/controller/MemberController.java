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
	
	 //회원가입으로 이동
	 @GetMapping("join") 
	 public String joinForm() {
		 log.debug("회원가입 이동했다 !!!!!!!!!!!");
		 return "/memberView/joinForm"; 
	}
	 
	 //회원가입하기
	 @PostMapping("join") 
	 public String join(Member member) {
		 log.debug("받아왔니? : {}",member);
		 
		 int result = service.join(member); 
		 log.debug("회원가입 성공했니? : {}", result);
		 
		 return "redirect:/";
		 
	 }
	 
	 //id중복확인
	@GetMapping("idcheck") 
	public String idcheck() {
		 log.debug("아이디체크 왔다 !!!!!!!!!!!!!!!");
		 return "/memberView/idcheck";
	}
	 
	//id중복체크 끝내면 값 넘기기
	@PostMapping("idcheck")
	public String idcheck(String searchId, Model model) {
		log.debug("검색할 ID : {}", searchId);
		boolean result = service.idcheck(searchId);
			
		model.addAttribute("searchId", searchId);
		model.addAttribute("result", result);
			
		return "/memberView/idcheck";
	}
	 
	@GetMapping("loginForm")
	public String loginForm() {
		return "/memberView/loginForm";
	}
		
	@GetMapping("updateInfo") 
	public String updateInfo(@AuthenticationPrincipal UserDetails user, Model model) {
		  
		log.debug("들고 온 정보 : {}", user); 
		Member member = service.getMemberInfo(user.getUsername()); 
		
		model.addAttribute("member",member);
		 
		return "/memberView/updateInfo"; 
		
	}
	 
	@PostMapping("updateInfo") 
	public String mypage(@AuthenticationPrincipal UserDetails user, Member member) { 
		log.debug("수정할 정보 : {}", member);
		
		member.setMemberid(user.getUsername()); 
		int result = service.updateMember(member); 
		log.debug("수정 완료?:{}",result);
		
		return "redirect:/"; 
	}	
		
}