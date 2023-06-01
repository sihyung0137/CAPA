package net.softsociety.spring03.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   public ArrayList<Company_info> selectAll(String searchWord) {
      
      return companyDAO.selectAll(searchWord);
   }


   //-----------------------------------------------

      
      @Override
      public List<Company_info> selectCompany(String company_name) {
         // TODO Auto-generated method stub
         return null;
      }


      @Override
      public Company_info readinfo(String company_name) {
         Company_info result = companyDAO.readinfo(company_name);
         return result;
      }


	@Override
	public Company_info selectOne(String company_name) {
		Company_info info = companyDAO.selectOne(company_name);
		return info;
	}


	@Override
	public int update(Company_info company_info) {
		int result = companyDAO.update(company_info);
		return result;
	}


	@Override
	public int delete(Company_info info) {
		int result = companyDAO.delete(info);
		return result;
	}


      
	
    
}
