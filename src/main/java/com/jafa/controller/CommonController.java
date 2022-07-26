package com.jafa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	@GetMapping("/accessError")
	public String accessDenied() {
		return "errorPage/accessError";
	}
	
	@GetMapping("/projectLogin")
	public String loginForm(String error, Model model) {
		if (error != null) {
			System.out.println(error);
			model.addAttribute("error", error);
		}
		return "member/login";
	}
	
	@GetMapping("/projectLogout")
	public String logout() {
		return "member/logout";
	}
}
