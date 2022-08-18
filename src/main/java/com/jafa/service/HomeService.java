package com.jafa.service;

import java.util.List;

import com.jafa.model.Board;
import com.jafa.model.UserBoard;

public interface HomeService {
	List<Board> bestBoardList();
	List<UserBoard> bestUserBoardList();
}
