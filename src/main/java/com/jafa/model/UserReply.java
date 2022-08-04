package com.jafa.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserReply {
	
	private Long bno;
	private Long rno;
	private String reply;
	private String replyer;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
}
