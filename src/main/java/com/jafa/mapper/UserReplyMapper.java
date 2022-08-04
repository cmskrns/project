package com.jafa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.model.Criteria;
import com.jafa.model.UserReply;

public interface UserReplyMapper {
	List<UserReply> getListAll();
	List<UserReply> getListWithPaging(@Param("cri") Criteria criteria,@Param("bno") Long bno);
	
	int insert(UserReply reply);
	UserReply read(Long rno);
	int delete(Long rno);
	int update(UserReply reply);
	
}
