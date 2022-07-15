package com.jafa.service;

import java.util.List;

import com.jafa.model.Criteria;
import com.jafa.model.Reply;

public interface ReplyService {
	List<Reply> getList(Criteria criteria, Long fno);
	
	int register(Reply reply);
	Reply get(Long rno);
	int modify(Reply reply);
	int remove(Long rno);
}
