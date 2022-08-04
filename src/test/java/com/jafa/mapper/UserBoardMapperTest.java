package com.jafa.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.config.AppTest;
import com.jafa.model.UserBoard;

public class UserBoardMapperTest extends AppTest {

	@Autowired
	UserBoardMapper mapper;
	
//	@Test
//	public void listTest() {
//		List<UserBoard> list = mapper.getList();
//		assertEquals(3,list.size());
//	}
	
	@Test
	public void getTest() {
		mapper.get(1L);
		
	}

}
