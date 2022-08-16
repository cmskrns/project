package com.jafa.service;

import java.util.List;

import com.jafa.model.AuthVO;
import com.jafa.model.Criteria;
import com.jafa.model.MemberVO;

public interface MemberService {
	
	List<MemberVO> getList(Criteria criteria);
	void register(MemberVO memberVO);
	void modify(MemberVO memberVO);
	void remove(String userId);
	int totalCount(Criteria criteria);
	MemberVO findById(String userId);
	
}
