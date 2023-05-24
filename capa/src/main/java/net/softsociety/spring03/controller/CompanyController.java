package net.softsociety.spring03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.CompanyService;

@Controller
@Slf4j
public class CompanyController {
	@Autowired
	CompanyService service;
	
	/**
	 * \
	 * 회사정보 폼 이동
	 * @return
	 */
	@GetMapping("joinCompanyForm")
	public String joinForm() {
		return "";
	}
}
	
