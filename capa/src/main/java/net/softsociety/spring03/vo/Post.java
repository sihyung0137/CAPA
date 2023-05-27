package net.softsociety.spring03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

//	create table CAPA_Post (
//		    postnum         number              primary key,
//		    boardnum        number              references CAPA_Board(boardnum),
//		    boardname       varChar2(200)       not null,           --타이틀(게시판이름 ex.시스템엔지니어)
//		    memberid        varChar2(50)        references CAPA_Member(memberid),
//		    title           varChar2(400)       not null,
//		    content         varChar2(4000)      not null,
//		    inputdate       date                not null,
//		    hits            number              not null,
//		    originalfile    varChar2(300)       not null,
//		    savedfile       varChar2(300)       not null
//		);
	
	
	int postnum;
	int boardnum;
	String boardname;
	String memeberid;
	String title;
	String content;
	String inputdate;
	int hits;
	
}
