package net.softsociety.spring03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	int displaynum;
	int postnum;
	int boardnum;
	String boardname;
	String memberid;
	String title;
	String content;
	String inputdate;
	int hits;
	String originalfile;
	String savedfile;
	
}
