package net.softsociety.spring03.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import net.softsociety.spring03.vo.Post;



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
	   ArrayList<Company_info> list = service.selectAll(searchWord);
	   
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
   public String joinCompanyForm(String company_name
		   , String homepage, Model model) {
	   
	   model.addAttribute("company_name", company_name);
	   model.addAttribute("homepage", homepage);
      
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
      
      return "redirect:companyForm";
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
	     
//	   String company_name = companyName;
	   
	   log.debug("회사 이름 가져옴?:{}",company_name);
	   
	  Company_info info = service.selectOne(company_name);
	  
	  log.debug("info : {}",info);
	  model.addAttribute("info",info);
	  
	   return "companyView/companyInfoForm";
   }
   
   
	/*
	 * @GetMapping("updateForm") public String updateForm(Company_info info) {
	 * 
	 * log.debug("html에서 받아온 값:{}", info);
	 * 
	 * int result = service.update(info);
	 * 
	 * log.debug("저장 성공????????:{}",result); return "redirect:companyInfoForm"; }
	 */
   @GetMapping("update")
   public String update(String company_name, Model model) {
	   Company_info info = service.readinfo(company_name);
	   
	   model.addAttribute("info", info);
	   return "/companyView/updateForm";
   }
   @PostMapping("update")
   public String update(
		   Company_info company_info
		   , @AuthenticationPrincipal UserDetails user
		   , MultipartFile upload) {
	   
	   log.debug("수정할 기업정보 : {}", company_info);
	   log.debug("파일정보: {}", upload);
	   
	   Company_info oldInfo = null;
	   String oldSavedfile = null;
	   String savedfile = null;
	   
	   // 첨부파일이 있는 경우 기존파일 삭제 후 새파일 저장
	   if (upload != null && !upload.isEmpty()) {
		   oldInfo = service.readinfo(company_info.getCompany_name());
		   oldSavedfile = oldInfo == null ? null : oldInfo.getSavedfile();
		   
		   savedfile = FileService.saveFile(upload, uploadPath);
		   company_info.setOriginalfile(upload.getOriginalFilename());
		   company_info.setSavedfile(savedfile);
		   log.debug("새파일:{}, 구파일:{}", savedfile, oldSavedfile);
	   }
	   
	   int result = service.update(company_info);
	   log.debug("컴페니인포================== : {} ", company_info);
	   if(result == 1 && savedfile != null) {
		   FileService.deleteFile(uploadPath + "/" + oldSavedfile); 
	   }
	   
	   return "redirect:/company/companyInfoForm?company_name=" + company_info.getCompany_name();
   }
   /**
	 * Post 삭제
	 * @param postnum 삭제할 글 번호
	 * @param user 인증정보
	 */
	@GetMapping ("delete")
	public String deletet(String company_name
			, @AuthenticationPrincipal UserDetails user) {
		//해당 번호의 글 정보 조회
		Company_info info = service.readinfo(company_name);
		
		if(info == null) {
			return "redirect:system";
		}
		
		//첨부된 파일명 확인
		String savedfile = info.getSavedfile();
		
//		info.setCompany_name(user.getUsername());
		
		//글 삭제
		int result = service.delete(info);
		
//		log.debug("post++++++++++++++++++++++++++++++++++++ : {}", post);
		//글 삭제 성공 and 첨부된 파일이 있는 경우 파일도 삭제
		if(result == 1 && savedfile != null) {
			FileService.deleteFile(uploadPath + "/" + savedfile);
		}
		
		return "redirect:companyForm?company_name=" + info.getCompany_name();
	}
	/**
	 * 회사등록요청
	 * @param 
	 */
	@PostMapping("requestCompany")
	public String requestCompany() {
     
      return "redirect:companyForm";
	}
}
   