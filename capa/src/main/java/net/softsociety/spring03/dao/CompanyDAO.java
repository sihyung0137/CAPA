package net.softsociety.spring03.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring03.vo.Company_info;

@Mapper
public interface CompanyDAO {

	int insertCompanyInfo(Company_info companyinfo);

	List<Company_info> selectAll(String company_name);

}
