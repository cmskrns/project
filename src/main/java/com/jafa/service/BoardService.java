package com.jafa.service;

import java.util.List;

import com.jafa.model.Board;
import com.jafa.model.Criteria;

public interface BoardService {
	List<Board> getList(Criteria criteria);
	Board get(Long fno);
	void register(Board board);
	void modify(Board board);
	void remove(Long fno);
	int totalCount(Criteria criteria);
	
}
