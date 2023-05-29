package net.softsociety.spring03.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.vo.Company_info;

@Mapper
public interface CompanyDAO {

   int insertCompanyInfo(Company_info company_info);
   
   List<Company_info> selectCompany(String company_name);

   ArrayList<Company_info> selectAll(Company_info info);

   Company_info readinfo(String company_name);

   
}