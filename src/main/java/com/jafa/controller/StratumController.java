package com.jafa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/stratum/*")
@Controller
public class StratumController {

	@GetMapping("/all")
	public void doAll() {
		System.out.println("아무나 사용가능");
	}
	
	@GetMapping("/user")
	public void doUser() {
		System.out.println("사용자만 가능");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		System.out.println("관리자만 가능");
	}
}
