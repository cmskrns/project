package com.jafa.mapper;

import java.util.List;

import com.jafa.model.Board;
import com.jafa.model.UserBoard;

public interface HomeMapper {
	List<Board> bestBoardList();
	List<UserBoard> bestUserBoardList();
}
