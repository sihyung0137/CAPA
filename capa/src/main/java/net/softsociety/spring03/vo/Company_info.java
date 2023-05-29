package net.softsociety.spring03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company_info {
   String   company_name; 
   int      sales; 
   int      employees; 
   String   c_address; 
   String   homepage; 
   int      salary_AVG; 
   String   filed; 
   String   scale; 
   String   info; 
   String   Edate;
   String  	originalfile;
   String 	savedfile;
   
}