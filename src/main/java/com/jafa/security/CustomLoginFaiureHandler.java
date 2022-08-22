package com.jafa.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomLoginFaiureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String loginId = request.getParameter("loginId");
		System.out.println(loginId);
		request.setAttribute("loginId", loginId);
		request.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
		request.getRequestDispatcher("/member/projectLogin").forward(request, response);
		
	}

}
