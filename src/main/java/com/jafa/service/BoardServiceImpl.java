package com.jafa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.mapper.BoardAttachMapper;
import com.jafa.mapper.BoardMapper;
import com.jafa.model.Board;
import com.jafa.model.BoardAttachVO;
import com.jafa.model.Criteria;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper mapper;
	
	@Autowired
	BoardAttachMapper attachMapper;
	
	
	@Override
	public List<Board> getList(Criteria criteria) {
		return mapper.getList(criteria);
	}

	@Transactional
	@Override
	public Board get(Long fno, boolean isAddCount) {
		if (isAddCount) {
			mapper.addViewCount(fno);
		}
		return mapper.get(fno);
	}

	@Transactional
	@Override
	public void register(Board board) {
		mapper.insert(board);
		if (board.getAttachList()==null || board.getAttachList().size()==0) {
			return;
		}
		board.getAttachList().forEach(attach -> {
			attach.setFno(board.getFno());
			attachMapper.insert(attach);
		});
	}

	@Transactional
	@Override
	public void modify(Board board) {
		attachMapper.deleteAll(board.getFno());
		mapper.update(board);
		if (board.getAttachList()!=null) {
			board.getAttachList().forEach(attach ->{
				attach.setFno(board.getFno());
				attachMapper.insert(attach);
			});
		}
	}

	@Transactional
	@Override
	public void remove(Long fno) {
		attachMapper.deleteAll(fno);
		mapper.delete(fno);
	}

	@Override
	public int totalCount(Criteria criteria) {
		return mapper.totalCount(criteria);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long fno) {
		return attachMapper.findByFno(fno);
	}


}
