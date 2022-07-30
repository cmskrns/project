package com.jafa.service;

import java.util.List;

import com.jafa.model.UserBoard;

public interface UserBoardService {

	List<UserBoard> getList();
	UserBoard get(Long bno);
	void register(UserBoard userBoard);
	void modify(UserBoard userBoard);
	void remove(Long bno);
	
}
