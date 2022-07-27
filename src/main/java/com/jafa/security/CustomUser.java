package com.jafa.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.jafa.model.MemberVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUser extends User{

	private static final long serialVersionUID = 6945415263396261945L;
	
	private MemberVO memberVO;
	
	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	
	public CustomUser(MemberVO vo) {
		super(vo.getUserId(), vo.getUserPw(),vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		this.memberVO = vo;
	}
	
	

}
