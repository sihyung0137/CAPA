package net.softsociety.spring03.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring03.vo.Member;

@Mapper
public interface MemberDAO {

	int joinInsert(Member m);
	
	Member selectId(String searchId);
}
