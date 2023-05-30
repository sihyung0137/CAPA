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
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.service.CompanyService;
import net.softsociety.spring03.util.FileService;
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
    * 회사정보검색 폼으로 이동
    * CompanyForm
    * @return
    */
   @GetMapping("companyForm")
   public String companyForm(Model model, Company_info info, String searchWord) {
	   
	   log.debug("찾을 단어 들고온 값 :{}", searchWord);
	   
	   //ArrayList<Company_info> list = service.selectAll(info);
	   ArrayList<Company_info> list = service.selectAll( searchWord);
	   
	   model.addAttribute("searchWord",searchWord);

	   model.addAttribute("list", list);
	   
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
   public String joinCompany(Company_info company_info, MultipartFile upload) {
      log.debug("가입정보 : {}", company_info);
      
      if (!upload.isEmpty()) {
			String savedfile = FileService.saveFile(upload, uploadPath);
			company_info.setOriginalfile(upload.getOriginalFilename());
			company_info.setSavedfile(savedfile);
		}
      
      int result = service.insertCompanyInfo(company_info);
      log.debug("회사 정보 등록 완료 ?:{}", result);
      
      return "redirect:CompanyForm";
   }

   
   
   /**
    * \
    * 검색명으로 정보조회
    * @return
    */
   @GetMapping("upload")
   public void upload(String company_name, Model model, HttpServletResponse response) {

      Company_info info = service.readinfo(company_name);
      if(info == null) {
         return;
      }
      
      String fullPath = uploadPath + "/" + info.getSavedfile();
      
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
   public String companyInfoForm(String company_name, Model model) {
	     
	   log.debug("회사 이름 가져옴?:{}",company_name);
	   
	  Company_info info = service.selectOne(company_name);
	  
	  model.addAttribute("info",info);
	  
	   return "/companyView/companyInfoForm";
   }
}
   