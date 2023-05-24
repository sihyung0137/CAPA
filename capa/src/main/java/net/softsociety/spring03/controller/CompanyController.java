package net.softsociety.spring03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.CompanyService;
import net.softsociety.spring03.vo.Company_info;

@Controller
@Slf4j
public class CompanyController {
	@Autowired
	CompanyService service;
	
	/**
	 * \
	 * 회사정보검색 폼으로 이동
	 * @return
	 */
	@GetMapping("searchCompanyForm")
	public String searchCompanyForm() {
		return "/companyView/searchCompanyForm";
	}
	/**
	 * \
	 * 회사정보입력 폼으로 이동
	 * @return
	 */
	@GetMapping("joinCompanyForm")
	public String joinCompanyForm() {
		return "/companyView/joinCompanyForm";
	}
	/**
	 * \ 회사정보입력 확인
	 * 
	 * @return
	 */
	@PostMapping("joinCompany")
	public String joinCompany(Company_info companyinfo) {
		log.debug("가입정보 : {}", companyinfo);
		int result = service.insertCompanyInfo(companyinfo);
		
		return "redirect:/";
	}
	/**
	 * \
	 * 회사정보 찾기 버튼
	 * @return
	 */
	@GetMapping("searchCompany")
	public String searchCompany(Model model,  
		@RequestParam(name = "company_name", defaultValue = "") String company_name) {
		List<Company_info> CompanyInfoList = service.selectCompany(company_name);
		if(CompanyInfoList.isEmpty()) {
			return "redirect:/";
		}
		model.addAttribute("CompanyInfoList",CompanyInfoList);
		
		return "/companyView/findCompany";
	}
	/**
	 * \
	 * 입력한 회사정보 보여주기
	 * @return
	 */
	@GetMapping("findCompany")
	public String findCompany(Model model,  
		@RequestParam(name = "company_name", defaultValue = "") String company_name) {
//		List<Company_info> CompanyInfoList = service.selectCompany(company_name);
//		if(CompanyInfoList.isEmpty()) {
//			return "redirect:/";
//		}
//		model.addAttribute("CompanyInfoList",CompanyInfoList);
//		
		return "/companyView/findCompanyForm";
	}
}
	
