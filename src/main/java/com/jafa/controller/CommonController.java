package com.jafa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	@GetMapping("/accessError")
	public String accessDenied(String msg) {
		System.out.println(msg);
		return "errorPage/accessError";
	}
	
	@RequestMapping("/projectLogin")
	public String loginForm(String error, Model model) {
		if (error != null) {
			System.out.println(error);
			model.addAttribute("error", error);
		}
		return "member/projectLogin";
	}
	
	@GetMapping("/projectLogout")
	public String logout() {
		return "member/projectLogout";
	}
}
