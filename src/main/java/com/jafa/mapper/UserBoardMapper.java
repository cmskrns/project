package com.jafa.mapper;

import java.util.List;

import com.jafa.model.UserBoard;

public interface UserBoardMapper {

	List<UserBoard> getList();
	UserBoard get(Long bno);
	void insert(UserBoard userBoard);
	void update(UserBoard userBoard);
	void delete(Long bno);
}
