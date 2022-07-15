package com.jafa.paging;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.config.AppTest;
import com.jafa.mapper.BoardMapper;
import com.jafa.model.Board;

public class SearchData extends AppTest {
	
	@Autowired
	BoardMapper mapper;
	
	@Test
	public void pageInsert() {
		for (int i = 1; i < 212; i++) {
			Board board = new Board();
			board.setWriter("관리자");
			board.setCategory("K-Food");
			board.setRtName("국밥"+i);
			board.setAddr("대구 북구 태전동");
			board.setDescription("설명란"+i);
			board.setAvgPrice(4500);
			board.setAvgDelivery("45분");
			mapper.insert(board);
			
		}
	}
}
