package net.softsociety.spring03.service;

import java.util.List;

import net.softsociety.spring03.vo.Company_info;

public interface CompanyService {

	int insertCompanyInfo(Company_info company_info);

	List<Company_info> selectCompany(String company_name);

}
