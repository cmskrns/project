package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.mapper.BoardMapper;
import com.jafa.mapper.ReplyMapper;
import com.jafa.model.Criteria;
import com.jafa.model.Reply;

import lombok.Setter;

@Service
public class ReplyServiceImpl implements ReplyService {

	private final static int REPLY_ADD = 1;
	private final static int REPLY_DEL = -1;
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Override
	public List<Reply> getList(Criteria criteria, Long fno) {
		return mapper.getListWithPaging(criteria,fno);
	}
	
	@Transactional
	@Override
	public int register(Reply reply) {
		boardMapper.updateReplyCnt(reply.getFno(), REPLY_ADD);
		return mapper.insert(reply);
	}

	@Override
	public Reply get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(Reply reply) {
		return mapper.update(reply);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		Reply reply = mapper.read(rno);
		boardMapper.updateReplyCnt(reply.getFno(), REPLY_ADD);
		return mapper.delete(rno);
	}

}
