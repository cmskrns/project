package com.jafa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.mapper.MemberMapper;
import com.jafa.model.AuthVO;
import com.jafa.model.MemberVO;

@Service
public class MemberServiceIpml implements MemberService {

	@Autowired
	MemberMapper mapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void register(MemberVO memberVO) {
		
		String pwEncoding = passwordEncoder.encode(memberVO.getUserPw());
		memberVO.setUserPw(pwEncoding);
		
		mapper.insert(memberVO);
		
		memberVO.setAuthList(new ArrayList<AuthVO>());
		memberVO.getAuthList().add(new AuthVO("ROLE_USER"));
		String userId = memberVO.getUserId();
		memberVO.getAuthList().forEach(v -> mapper.authenticate(userId, v.getAuth()));
	}

	@Override
	public MemberVO findById(String userId) {
		return mapper.findByUserId(userId);
	}

	@Override
	public void modify(MemberVO memberVO) {
		mapper.update(memberVO);
		
	}

	@Override
	public void remove(MemberVO memberVO) {
		mapper.delete(memberVO);
	}

}
