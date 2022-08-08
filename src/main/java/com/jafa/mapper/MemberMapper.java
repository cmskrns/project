package com.jafa.mapper;

import org.apache.ibatis.annotations.Param;

import com.jafa.model.MemberVO;

public interface MemberMapper {
	
	MemberVO read(String userId);
	void insert(MemberVO memberVO);
	void delete(MemberVO memberVO);
	void update(MemberVO memberVO);
	MemberVO findByUserId(String userId);
	void authenticate(@Param("userId") String userId, @Param("auth") String auth);
	
}
