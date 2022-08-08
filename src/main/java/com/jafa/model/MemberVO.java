package com.jafa.model;

import java.time.LocalDateTime;
import java.util.List;

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
public class MemberVO {
	
	private String userId;
	private	String userPw;
	private String userName;
	private String userEmail;
	private String addr;
	private String phoneNumber;
	private String age;
	private String natalDay;
	private GENDER gender;
	private boolean enabled;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private List<AuthVO> authList;
	enum GENDER {M,W};
}
