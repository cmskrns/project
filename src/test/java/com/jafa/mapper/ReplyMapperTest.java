package com.jafa.mapper;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.config.AppTest;
import com.jafa.model.Criteria;
import com.jafa.model.Reply;

public class ReplyMapperTest extends AppTest{
	
	@Autowired
	ReplyMapper mapper;
	
	@Test
	@Ignore
	public void test() {
		System.out.println(mapper.getListAll());
	}
	
	@Test
	@Ignore
	public void insertTest() {
		Reply reply = new Reply();
			reply.setFno(1L);
			reply.setReply("테스트1");
			reply.setReplyer("테스터1");
			mapper.insert(reply);
		
	}
	
	@Test
	@Ignore
	public void readTest() {
		System.out.println(mapper.read(2L));
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		mapper.delete(3L);
	}

	@Test
	@Ignore
	public void updateTest() {
		Reply reply = new Reply();
			reply.setRno(4L);
			reply.setReply("수정테스트");
			reply.setReplyer("수정테스터");
			mapper.update(reply);
	}
	
	@Test
	public void withPageTest() {
		Criteria cri = new Criteria();
		List<Reply> replies = mapper.getListWithPaging(cri, 1L);
		replies.forEach(reply -> System.out.println(reply));
	}
	
}
