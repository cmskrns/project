package com.jafa.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		List<String> roleNames = new ArrayList<String>();
		
		authentication.getAuthorities().forEach(authority ->{
			roleNames.add(authority.getAuthority());
		});
		
		if (roleNames.contains("ROLE_ADMIN")) {
			System.out.println("관리자 로그인");
			response.sendRedirect(request.getContextPath()+"/stratum/admin");
			return;
		}
		
		if (roleNames.contains("ROLE_USER")) {
			System.out.println("유저 로그인");
			response.sendRedirect(request.getContextPath()+"/stratum/user");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/");
	}

}
