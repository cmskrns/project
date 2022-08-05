package com.jafa.mapper;

import java.util.List;

import com.jafa.model.UserBoardAttachVO;


public interface UserBoardAttachMapper {
	
	public void insert(UserBoardAttachVO vo);
	public void delete(String uuid);
	public List<UserBoardAttachVO> findByBno(Long bno);
	public void  deleteAll(Long fno);
	
	List<UserBoardAttachVO> getOldFiles();
}
