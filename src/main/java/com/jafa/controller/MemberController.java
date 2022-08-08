package com.jafa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jafa.model.MemberVO;
import com.jafa.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/memberinsert")
	public String join() {
		return "member/memberinsert";
	}
	
	@PostMapping("/memberinsert")
	public String register(@Valid MemberVO member, Errors errors) {
		service.register(member);
		return "redirect:/";
	}
	
	@GetMapping("/myPage/{userId}")
	public String myPage(@PathVariable String userId) {
		return "member/myPage";
	}
	
	//아이디 조회
	@GetMapping("/idCheck/{userId}")
	@ResponseBody
	public boolean findByUserId(@PathVariable String userId) {
		MemberVO findById = service.findById(userId);
		if (findById != null) {
			System.out.println("아이디 중복");
			return false;
		}
		return true;
		
	}
}
