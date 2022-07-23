package com.jafa.mapper;

import java.util.List;

import com.jafa.model.BoardAttachVO;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachVO vo);
	public void delete(String uuid);
	public List<BoardAttachVO> findByFno(Long fno);
	public void  deleteAll(Long fno);
	
	List<BoardAttachVO> getOldFiles();
}
