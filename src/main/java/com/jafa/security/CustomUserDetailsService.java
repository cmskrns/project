package com.jafa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jafa.mapper.MemberMapper;
import com.jafa.model.MemberVO;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("???"+username);
		
		MemberVO vo = memberMapper.read(username);
		
		System.out.println("??"+vo);
		
		return vo == null ? null : new CustomUser(vo);
	}

}
