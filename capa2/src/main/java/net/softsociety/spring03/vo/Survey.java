package net.softsociety.spring03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

	int surveynum;
	String company_name;
	String memberid;
	String q1;
	String q2;
	String q3;
}
