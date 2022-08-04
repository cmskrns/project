package com.jafa.service;

import java.util.List;

import com.jafa.model.Criteria;
import com.jafa.model.UserReply;

public interface UserReplyService {
	List<UserReply> getList(Criteria criteria, Long bno);
	
	int register(UserReply reply);
	UserReply get(Long rno);
	int modify(UserReply reply);
	int remove(Long rno);
}
