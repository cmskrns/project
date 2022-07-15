package com.jafa.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.config.AppTest;
import com.jafa.model.Board;
import com.jafa.model.Criteria;

public class BoardMapperTest extends AppTest {

	@Autowired
	BoardMapper mapper;
	
	@Test
	public void getListTest() {
//		List<Board> list = mapper.getList();
//		assertEquals(1, list.size());
	}
}
