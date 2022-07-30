package com.jafa.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.config.AppTest;
import com.jafa.model.UserBoard;

public class UserBoardServiceIpmlTest extends AppTest{

	@Autowired
	UserBoardService service;
	
	@Test
	public void getListTest() {
		List<UserBoard> list = service.getList();
		assertEquals(3, list.size());
	}

}
