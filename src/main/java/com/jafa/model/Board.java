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
public class Board {
	private Long fno;
	private String category;
	private String writer;
	private String rtName;
	private String addr;
	private String description;
	private int avgPrice;
	private String avgDelivery;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	private int replyCnt;
	private List<Reply> replyList; 
	private List<BoardAttachVO> attachList; 
}