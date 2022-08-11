package com.jafa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jafa.mapper.MemberMapper;
import com.jafa.model.Criteria;
import com.jafa.model.MemberVO;
import com.jafa.model.PageMaker;
import com.jafa.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberMapper mapper;
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/projectLogin")
	public String loginForm(MemberVO memberVO, String error, Model model) {
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
	
	@GetMapping("/memberinsert")
	public String join(MemberVO member) {
		return "member/memberinsert";
	}
	
	@GetMapping("/memberList")
	public String memberList(Criteria criteria, Model model) {
		//PageMaker pageMaker = new PageMaker(criteria, mapper.totalCount)
		model.addAttribute("list", service.getList());
		return "member/memberList";
	}
	
	@PostMapping("/memberinsert")
	public String register(@Valid MemberVO member, Errors errors) {
		if (errors.hasErrors()) {
			return "member/memberinsert";
		}
		service.register(member);
		return "redirect:/";
	}
	
	@PostMapping("/memberRemove")
	public String remove(String userId) {
		service.remove(userId);
		return "redirect:memberList";
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
