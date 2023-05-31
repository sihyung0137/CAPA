package net.softsociety.spring03.service;

import java.util.ArrayList;
import java.util.List;

import net.softsociety.spring03.vo.Company_info;

public interface CompanyService {

   int insertCompanyInfo(Company_info company_info);

   List<Company_info> selectCompany(String company_name);

   ArrayList<Company_info> selectAll(String searchWord);

   Company_info readinfo(String company_name);

   Company_info selectOne(String company_name);

//   int update(Company_info info);
   
}