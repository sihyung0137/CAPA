package net.softsociety.spring03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	int boardnum;
	String boardname;
	String memberid;
	String boardtype;
	String boardjob;

}
