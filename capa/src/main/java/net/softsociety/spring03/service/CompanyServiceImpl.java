package net.softsociety.spring03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.dao.CompanyDAO;
import net.softsociety.spring03.vo.Company_info;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	CompanyDAO companyDAO;

	@Override
	public int insertCompanyInfo(Company_info companyinfo) {
		int result = companyDAO.insertCompanyInfo(companyinfo);
		return result;
	}

	@Override
	public List<Company_info> selectCompany(String company_name) {
		List<Company_info> result = companyDAO.selectCompany(company_name);
		return result;
	}
	
	
	
	
}
