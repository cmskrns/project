package com.jafa.service;

import java.util.List;

import com.jafa.model.Criteria;
import com.jafa.model.UserBoard;
import com.jafa.model.UserBoardAttachVO;

public interface UserBoardService {

	List<UserBoard> getList(Criteria criteria);
	UserBoard get(Long bno);
	void register(UserBoard userBoard);
	void modify(UserBoard userBoard);
	void remove(Long bno);
	int totalCount(Criteria criteria);
	
	List<UserBoardAttachVO> getAttachList(Long bno);
}
