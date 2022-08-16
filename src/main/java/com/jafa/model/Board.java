package com.jafa.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

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
public class Board {
	private Long fno;
	private String category;
	private String writer;
	
	@NotBlank(message = "상호를 입력해 주세요")
	private String rtName;
	@NotEmpty(message = "주소를 입력하세요")
	private String addr;
	@NotEmpty(message = "설명란을 입력하세요")
	private String description;
	@PositiveOrZero(message = "양수와 0만 가능합니다")
	private int avgPrice;
	@NotBlank(message = "평균 배달시간을 입력하세요")
	private String avgDelivery;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	private Long viewCount;
	private int replyCnt;
	private List<Reply> replyList; 
	private List<BoardAttachVO> attachList; 
}