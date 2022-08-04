package com.jafa.paging;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.config.AppTest;
import com.jafa.mapper.BoardMapper;
import com.jafa.mapper.UserBoardMapper;
import com.jafa.model.Board;
import com.jafa.model.UserBoard;

public class pageInsert extends AppTest{

	@Autowired
	BoardMapper mapper;
	
	@Autowired
	UserBoardMapper userBoardMapper;
	
	@Test
	@Ignore
	public void pageInsert() {
		for (int i = 1; i < 412; i++) {
			Board board = new Board();
			board.setWriter("관리자");
			board.setCategory("K-Food");
			board.setRtName("한식"+i);
			board.setAddr("대구 칠곡");
			board.setDescription("설명란"+i);
			board.setAvgPrice(4500);
			board.setAvgDelivery("45분");
			mapper.insert(board);
			
		}
	}
	
	@Test
	public void userPageInsert() {
		for (int i = 1; i < 100; i++) {
			UserBoard board = new UserBoard();
			board.setWriter("박무개"+i);
			board.setCategory("Free");
			board.setTitle("아니야"+i);
			board.setContent("아야야");
			userBoardMapper.insert(board);
		}
	}
}
