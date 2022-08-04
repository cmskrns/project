package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.mapper.UserBoardMapper;
import com.jafa.mapper.UserReplyMapper;
import com.jafa.model.Criteria;
import com.jafa.model.UserReply;

import lombok.Setter;

public class UserReplyServiceIpml implements UserReplyService {

	private final static int REPLY_ADD = 1;
	private final static int REPLY_DEL = -1;
	
	@Setter(onMethod_=@Autowired)
	private UserReplyMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private UserBoardMapper boardMapper;
	
	@Override
	public List<UserReply> getList(Criteria criteria, Long bno) {
		return mapper.getListWithPaging(criteria, bno);
	}

	@Transactional
	@Override
	public int register(UserReply reply) {
		boardMapper.updateReplyCnt(reply.getBno(), REPLY_ADD);
		return mapper.insert(reply);
	}

	@Override
	public UserReply get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(UserReply reply) {
		return mapper.update(reply);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		UserReply reply = mapper.read(rno);
		boardMapper.updateReplyCnt(reply.getBno(), REPLY_ADD);
		return mapper.delete(rno);
	}

}
