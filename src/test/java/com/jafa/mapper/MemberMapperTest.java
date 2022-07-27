package com.jafa.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.config.AppTest;
import com.jafa.model.MemberVO;

public class MemberMapperTest extends AppTest{

	@Autowired
	MemberMapper mapper;
	
	@Test
	public void test() {
		
		MemberVO vo = mapper.read("admin");
		vo.getAuthList().forEach(authVO ->System.out.println(authVO));
	}

}
