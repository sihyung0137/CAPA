package net.softsociety.spring03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	
	String memberid;
	String replytext;
	String inputdate;
	int	   replynum;
	int	   boardnum;
	int    postnum;
}
