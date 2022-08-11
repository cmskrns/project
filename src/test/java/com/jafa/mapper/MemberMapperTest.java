package com.jafa.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.config.AppTest;
import com.jafa.model.MemberVO;

public class MemberMapperTest extends AppTest{

	@Autowired
	MemberMapper mapper;
	
	@Test
	@Ignore
	public void test() {
		
		MemberVO vo = mapper.read("admin");
		vo.getAuthList().forEach(authVO ->System.out.println(authVO));
	}
	
	@Test
	public void ListTest() {
		List<MemberVO> list = mapper.memberList();
		assertEquals(1,list.size());
	}

}
