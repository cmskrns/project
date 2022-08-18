package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.mapper.HomeMapper;
import com.jafa.model.Board;
import com.jafa.model.UserBoard;

public class HomeServiceImpl implements HomeService {

	@Autowired
	HomeMapper mapper;
	
	@Override
	public List<Board> bestBoardList() {
		return mapper.bestBoardList();
	}

	@Override
	public List<UserBoard> bestUserBoardList() {
		return mapper.bestUserBoardList();
	}

}
