package net.softsociety.spring03.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.dao.MemberDAO;
import net.softsociety.spring03.vo.Member;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO dao;
	
	@Autowired
    private PasswordEncoder passwordEncoder;



	@Override
	public int join(Member member) {
		//가입폼에 입력한 비밀번호를 암호화
		String encodedPassword = passwordEncoder.encode(member.getMemberpw());
		log.debug("암호화된 비번 {} : {}", member.getMemberpw(), encodedPassword);
		member.setMemberpw(encodedPassword);
		
		int result = dao.join(member);
		return result;
	}


	@Override
	public boolean idcheck(String memberid) {
		Member member = dao.selectOne(memberid);
		return member == null? true : false;
	}


	
	 @Override 
	 public Member getMemberInfo(String username) { 
		 return dao.getMemberInfo(username); 
	}
	
	
	 
	 @Override 
	 public int updateMember(Member member) { 
		 int result = dao.updateMember(member); 
		 return result; 
	 }

}
