package com.jafa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.model.Board;
import com.jafa.model.Criteria;
import com.jafa.model.Reply;

public interface BoardMapper {
	List<Board> getList(@Param("cri") Criteria criteria);
	List<Reply> getReplyList(Long fno);
	
	Board get(Long fno);
	void insert(Board board);
	void update(Board board);
	void delete(Long fno);
	int totalCount(@Param("cri") Criteria criteria);
	
	void updateReplyCnt(@Param("fno") Long fno,@Param("amount") int amount);
	
	
}
