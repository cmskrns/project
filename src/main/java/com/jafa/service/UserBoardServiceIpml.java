package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.mapper.UserBoardMapper;
import com.jafa.model.UserBoard;

@Service
public class UserBoardServiceIpml implements UserBoardService {
	
	@Autowired
	UserBoardMapper mapper;
	
	@Override
	public List<UserBoard> getList() {
		return mapper.getList();
	}

	@Override
	public UserBoard get(Long bno) {
		return mapper.get(bno);
	}

	@Override
	public void register(UserBoard userBoard) {
		mapper.insert(userBoard);
	}

	@Override
	public void modify(UserBoard userBoard) {
		mapper.update(userBoard);
	}

	@Override
	public void remove(Long bno) {
		mapper.delete(bno);
	}

}
