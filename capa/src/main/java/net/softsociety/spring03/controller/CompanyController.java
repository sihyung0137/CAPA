package net.softsociety.spring03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.CompanyService;
import net.softsociety.spring03.vo.Company_info;

@Controller
@Slf4j
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService service;
	
	/**
	 * \
	 * 회사정보검색 폼으로 이동
	 * searchCompanyForm
	 * @return
	 */
	@GetMapping("searchCompanyForm")
	public String searchCompanyForm() {
		return "/companyView/searchCompanyForm";
	}
	/**
	 * \
	 * 회사정보입력 폼으로 이동
	 * joinCompanyForm
	 * @return
	 */
	@GetMapping("joinCompanyForm")
	public String joinCompanyForm() {
		return "/companyView/joinCompanyForm";
	}
	/**
	 * \
	 * 회사정보입력 확인 버튼
	 * 회사정보 데이터를 DB에 넣은 후 searchCompanyForm으로 돌아감
	 * @return
	 */
	@PostMapping("joinCompany")
	public String joinCompany(Company_info company_info) {
		log.debug("가입정보 : {}", company_info);
		int result = service.insertCompanyInfo(company_info);
		
		return "/companyView/searchCompanyForm";
	}

	/**
	 * \
	 * 정보 검색 버튼
	 * 검색한 회사의 정보를 간소화하여 리스트로 보여주는 창
	 * companyListForm
	 * @return
	 */
	@GetMapping("companyListForm")
	public String companyListForm(Model model,
			@RequestParam(name = "company_name", defaultValue = "") String company_name) {
		List<Company_info> CompanyInfoList = service.selectCompany(company_name);
		if(CompanyInfoList.isEmpty()) {
			return "redirect:/";
		}
		model.addAttribute("CompanyInfoList",CompanyInfoList);
		
		return "/companyView/companyListForm";
	}
	
	/**
	 * \
	 * 회사정보 상세히 보여주고 리뷰를 보여주는 창 
	 * @return
	 */
	@GetMapping("companyInfoForm")
	public String companyInfoForm() {
		return "/companyView/companyInfoForm";
	}
}
	
