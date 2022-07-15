package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.mapper.ReplyMapper;
import com.jafa.model.Criteria;
import com.jafa.model.Reply;

import lombok.Setter;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public List<Reply> getList(Criteria criteria, Long fno) {
		return mapper.getListWithPaging(criteria,fno);
	}

	@Override
	public int register(Reply reply) {
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

	@Override
	public int remove(Long rno) {
		return mapper.delete(rno);
	}

}
