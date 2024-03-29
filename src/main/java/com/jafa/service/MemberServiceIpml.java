package com.jafa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.mapper.AuthMapper;
import com.jafa.mapper.MemberMapper;
import com.jafa.model.AuthVO;
import com.jafa.model.Criteria;
import com.jafa.model.MemberVO;

@Service
public class MemberServiceIpml implements MemberService {

	@Autowired
	MemberMapper mapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthMapper authMapper;
	
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

	@Transactional
	@Override
	public void modify(MemberVO memberVO) {
		String pwEncoding = passwordEncoder.encode(memberVO.getUserPw());
		memberVO.setUserPw(pwEncoding);
		mapper.update(memberVO);
		
	}

	@Transactional
	@Override
	public void remove(String userId) {
		 authMapper.delete(userId);
		 mapper.delete(userId);
	}

	@Override
	public List<MemberVO> getList(Criteria criteria) {
		return mapper.memberList(criteria);
	}

	@Override
	public int totalCount(Criteria criteria) {
		return mapper.totalCount(criteria);
	}

}
