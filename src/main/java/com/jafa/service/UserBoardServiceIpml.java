package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.mapper.UserBoardAttachMapper;
import com.jafa.mapper.UserBoardMapper;
import com.jafa.model.Criteria;
import com.jafa.model.UserBoard;
import com.jafa.model.UserBoardAttachVO;

@Service
public class UserBoardServiceIpml implements UserBoardService {
	
	@Autowired
	UserBoardMapper mapper;
	
	@Autowired
	UserBoardAttachMapper attachMapper;
	
	@Override
	public List<UserBoard> getList(Criteria criteria) {
		return mapper.getList(criteria);
	}

	@Transactional
	@Override
	public UserBoard get(Long bno, boolean isAddCount) {
		if (isAddCount) {
			mapper.addViewCount(bno);
		}
		return mapper.get(bno);
	}

	@Transactional
	@Override
	public void register(UserBoard userBoard) {
		mapper.insert(userBoard);
		if (userBoard.getAttachList()==null || userBoard.getAttachList().size()==0) {
			return;
		}
		userBoard.getAttachList().forEach(attach ->{
			attach.setBno(userBoard.getBno());
			attachMapper.insert(attach);
		});
	}

	@Transactional
	@Override
	public void modify(UserBoard userBoard) {
		attachMapper.deleteAll(userBoard.getBno());
		mapper.update(userBoard);
		if (userBoard.getAttachList()!=null) {
			userBoard.getAttachList().forEach(attach -> {
				attach.setBno(userBoard.getBno());
				attachMapper.insert(attach);
			});
		}
	}

	@Transactional
	@Override
	public void remove(Long bno) {
		attachMapper.deleteAll(bno);
		mapper.delete(bno);
	}

	@Override
	public int totalCount(Criteria criteria) {
		return mapper.totalCount(criteria);
	}

	@Override
	public List<UserBoardAttachVO> getAttachList(Long bno) {
		return attachMapper.findByBno(bno);
	}

}
