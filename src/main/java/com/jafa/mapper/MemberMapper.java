package com.jafa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.model.MemberVO;

public interface MemberMapper {
	
	List<MemberVO> memberList();
	MemberVO read(String userId);
	
	void insert(MemberVO memberVO);
	void delete(String userId);
	void update(MemberVO memberVO);
	MemberVO findByUserId(String userId);
	void authenticate(@Param("userId") String userId, @Param("auth") String auth);
	
}
