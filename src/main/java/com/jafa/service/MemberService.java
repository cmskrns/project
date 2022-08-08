package com.jafa.service;

import com.jafa.model.MemberVO;

public interface MemberService {
	
	void register(MemberVO memberVO);
	void modify(MemberVO memberVO);
	void remove(MemberVO memberVO);
	MemberVO findById(String userId);
}
