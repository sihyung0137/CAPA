package net.softsociety.spring03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.dao.MemberDAO;
import net.softsociety.spring03.vo.Member;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDAO dao;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Override
	public int joinInsert(Member m) {
		// 비밀번호 암호화 필요
		String EncoderedPw = pwEncoder.encode(m.getPassword());
		m.setMemberpw(EncoderedPw);
			
		boolean result2 = pwEncoder.matches("111", EncoderedPw); //(비교할 비밀번호, 암호화된 비밀번호)
		log.debug("비밀번호 결과: {}", result2);
		
		int result = dao.joinInsert(m);
		return result;
	}

	@Override
	public boolean selectId(String searchId) {
		Member m = dao.selectId(searchId);
		return m != null ? false : true;
	}
}
