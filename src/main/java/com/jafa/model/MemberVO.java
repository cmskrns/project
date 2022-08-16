package com.jafa.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.jafa.common.FieldMatch;

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
@FieldMatch(first = "confirmUserPw", second = "userPw", message = "비밀번호가 일치하지 않습니다.")
@Builder
public class MemberVO {
	
	@NotBlank(message = "아이디를 입력해주세요")
	@Pattern(regexp = "^[a-zA-z0-9]{4,12}$", message = "아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다.")
	private String userId;
	
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호는 숫자, 문자, 특수문자 포함 8~15자리 이내이어야 합니다.")
	private	String userPw;
	private String confirmUserPw;
	
	public boolean passwordMatch(String userPw) {
		return this.userPw.equals(userPw);
	}
	
	@NotEmpty(message = "이름을 입력하세요")
	@Pattern(regexp = "^[가-힣|a-z|A-Z]{2,5}$", message = "이름은 한글 또는 영어 2~5글자이어야 합니다.")
	private String userName;
	
	@Email(message = "이메일 형식이 아닙니다")
	@Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일 형식으로 입력해주세요")
	private String userEmail;
	
	@NotEmpty(message = "주소를 입력하세요")
	private String addr;
	
	@NotEmpty(message = "전화번호를 입력하세요")
	@Pattern(regexp = "^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$", message = "전화번호를 올바르게 입력하세요.")
	private String phoneNumber;
	
	@NotEmpty(message = "생년월일을 입력하세요")
	private String natalDay;
	
	private String gender;
	private boolean enabled;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private List<AuthVO> authList;
	
}
