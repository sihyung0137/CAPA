package net.softsociety.spring03.dao;


import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring03.vo.Member;

@Mapper
public interface MemberDAO {

	int join(Member member);

	public Member selectOne(String memberid);

	Member getMemberInfo(String username);
	  
	int updateMember(Member member);

}
