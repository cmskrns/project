package com.jafa.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBoard {
	
	private Long bno;
	private String category;
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	@NotBlank(message = "내용을 입력하세요")
	private String content;
	
	private String writer;
	private Long viewCount;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	private int replyCnt;
	private List<UserReply> replyList;
	private List<UserBoardAttachVO> attachList;
}
