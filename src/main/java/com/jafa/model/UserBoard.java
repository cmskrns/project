package com.jafa.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserBoard {
	
	private Long bno;
	private String category;
	private String title;
	private String content;
	private String writer;
	private Long viewCount;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	private int replyCnt;
	private List<UserReply> replyList;
	private List<UserBoardAttachVO> attachList;
}
