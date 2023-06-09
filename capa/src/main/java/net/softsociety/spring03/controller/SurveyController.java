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
import net.softsociety.spring03.service.SurveyService;
import net.softsociety.spring03.vo.Review;
import net.softsociety.spring03.vo.Survey;

@Slf4j
@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	// 설문지 기능 구현 서비스 객체
	@Autowired
	SurveyService service;
	
	/**
	 * 기업평가 목록으로 이동
	 * @param 
	 * @return
	 */
	@GetMapping("list")
	public String queList(Model model) {
		
		// 회사 기업평가 글 목록 조회
		ArrayList<Survey> surveylist = service.surveyList();
		log.debug("surveylist:{} ", surveylist);
		
		model.addAttribute("surveylist", surveylist);
				
		return "/surveyView/list";
	}
	
	@GetMapping("AvgReview")
	public String listReview(Model model, String company_name) {
		
		// 회사 별점 조회
		Review reviewAvg = service.selectAvgReview(company_name);
		log.debug("reviewAvg:{} ", reviewAvg);
		
		model.addAttribute("reviewAvg", reviewAvg);
		
		return "/surveyView/list";
	}
	
	/**
	 * 기업평가 읽기
	 * @param surveynum 읽을 후기번호
	 * @return 후기 글 하나
	 */
	@GetMapping("read")
	public String read(
			Model model
			, @RequestParam(name="surveynum", defaultValue = "0") int surveynum) {
		
		Survey survey = service.read(surveynum);
		
		// 현재 설문지의 별점평가 추가
		
		// 결과를 모델에 담아서 HTML에서 출력
		model.addAttribute("survey", survey);
		model.addAttribute(null, survey);
		
		return "/surveyView/read";
	}
	
	/**
	 * 기업평가 작성 폼으로 이동
	 */
	@GetMapping("write")
	public String write() {
		return "/surveyView/writeForm";
	}
	
	/**
	 * 기업평가 저장
	 * @param survey 사용자가 폼에 입력한 기업평가 정보
	 * @param user 인증정보
	 */
	@PostMapping("write")
	public String write(
			Survey survey
			,@AuthenticationPrincipal UserDetails user) {
		
		log.debug("저장할 기업평가 정보 : {}", survey);
		
		survey.setMemberid(user.getUsername());
		int result = service.write(survey);
		
		return "redirect:/survey/list";
	}
	
	/**
	 * 기업평가 삭제
	 * @param survey 사용자가 폼에 입력한 기업평가를 삭제
	 * @param user
	 * @return
	 */
	@GetMapping("delete")
	public String delete(int boardnum, @AuthenticationPrincipal UserDetails user) {
		
		// 해당 번호의 기업평가 조회
		Survey survey = service.read(boardnum);
		
		if(survey == null) {
			return "redirect:list";
		}
		
		survey.setMemberid(user.getUsername());
		
		int result = service.delete(survey);
		
		return "redirect:list";
	}
	
	@PostMapping("writeReview")
	public String writeReview(Review review, @AuthenticationPrincipal UserDetails user) {
		
		review.setMemberid(user.getUsername());
	
		log.debug("저장할 별점 정보 : {}", review);
		
		int result = service.writeReview(review);

		return "redirect:/survey/read?surveynum=" + review.getSurveynum();
	}
	
	
	
}