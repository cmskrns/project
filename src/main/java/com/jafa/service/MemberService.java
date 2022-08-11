package com.jafa.service;

import java.util.List;

import com.jafa.model.MemberVO;

public interface MemberService {
	
	List<MemberVO> getList();
	void register(MemberVO memberVO);
	void modify(MemberVO memberVO);
	void remove(String userId);
	MemberVO findById(String userId);
}
