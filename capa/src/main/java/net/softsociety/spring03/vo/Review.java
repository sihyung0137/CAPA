package net.softsociety.spring03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	int reviewnum;
	String company_name;
	String memberid;
	int surveynum;
	int welfare;
	int culture;
	int salary_STF;
	int growth;
	int wld;
}
