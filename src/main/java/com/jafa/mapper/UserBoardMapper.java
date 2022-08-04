package com.jafa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.model.Criteria;
import com.jafa.model.UserBoard;

public interface UserBoardMapper {

	List<UserBoard> getList(@Param("cri") Criteria criteria);
	UserBoard get(Long bno);
	void insert(UserBoard userBoard);
	void update(UserBoard userBoard);
	void delete(Long bno);
	int totalCount(@Param("cri") Criteria criteria);
	
	void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
