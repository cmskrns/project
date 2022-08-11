package com.jafa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jafa.model.MemberVO;

@Controller
public class CommonController {
	
	@GetMapping("/accessError")
	public String accessDenied(String msg) {
		System.out.println(msg);
		return "errorPage/accessError";
	}
	
}
