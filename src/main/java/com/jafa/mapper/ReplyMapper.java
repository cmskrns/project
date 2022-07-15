package com.jafa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.model.Criteria;
import com.jafa.model.Reply;

public interface ReplyMapper {
	List<Reply> getListAll();
	List<Reply> getListWithPaging(@Param("cri") Criteria criteria,@Param("fno") Long fno);
	
	int insert(Reply reply);
	Reply read(Long rno);
	int delete(Long rno);
	int update(Reply reply);
	
}
