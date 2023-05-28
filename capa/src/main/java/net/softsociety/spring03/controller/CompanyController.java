package net.softsociety.spring03.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.CompanyService;
import net.softsociety.spring03.vo.Company_info;

@Controller
@Slf4j
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService service;
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	
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
//	@GetMapping("companyListForm")
//	public String companyListForm(Model model,
//			@RequestParam(name = "company_name", defaultValue = "") String company_name) {
//		
//		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		log.debug("회사 정보 찾기 :{}",company_name);
//		
//		List<Company_info> list = service.selectCompany(company_name);
//		log.debug("회사정보  !!!! :{}",list);
//		
//		if(list.isEmpty()) {
//			return "redirect:searchCompanyForm";
//		}
//		
//		model.addAttribute("list",list);
//		//별점을 나중에 가져와야함 
//		
//		
//		
//		return "redirect:searchCompanyForm";
//	}
	/**
	 * \
	 * 회사정보검색 폼으로 이동
	 * searchCompanyForm
	 * @return
	 */
	@GetMapping("CompanyForm")
	public String CompanyForm(Model model, 
			Company_info info) {
		
		ArrayList<Company_info> list = service.selectAll();
		
		model.addAttribute("list", list);
		
		
		return "/companyView/searchCompanyForm";
	}
	/**
	 * \
	 * 검색명으로 정보조회
	 * @return
	 */
	@GetMapping("upload")
	public void upload(String company_name, Model model, HttpServletResponse response) {
		log.debug("dsfdaffdsasdfasd");
		Company_info info = service.readinfo(company_name);
		if(info == null) {
			return;
		}
		
		String fullPath = uploadPath + "/" + info.getLogo();
		
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
			try {
				filein = new FileInputStream(fullPath);
				fileout = response.getOutputStream();
				
				FileCopyUtils.copy(filein, fileout);
				filein.close();
				fileout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
