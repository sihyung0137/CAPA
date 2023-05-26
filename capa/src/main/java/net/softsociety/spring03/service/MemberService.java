package net.softsociety.spring03.service;


import net.softsociety.spring03.vo.Member;

public interface MemberService {

	int join(Member member);

	boolean idcheck(String memberid);
	
	Member getMemberInfo(String username);
	 
	int updateMember(Member member);

}
