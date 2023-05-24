package net.softsociety.spring03.service;

import net.softsociety.spring03.vo.Member;

public interface MemberService {

	int joinInsert(Member m);

	boolean selectId(String searchId);

}
