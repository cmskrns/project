package com.jafa.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.mapper.MemberMapper;
import com.jafa.model.AuthVO;
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
	
	@Autowired
	HttpSession httpSession; 
	
	
	//로그인,로그아웃
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
	
	//회원리스트
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/memberList")
	public String memberList(Criteria criteria, Model model) {
		PageMaker pageMaker = new PageMaker(criteria, mapper.totalCount(criteria));
		model.addAttribute("list", service.getList(criteria));
		model.addAttribute("pageMaker", pageMaker);
		return "member/memberList";
	}

	//회원가입
	@GetMapping("/memberinsert")
	public String join(MemberVO member) {
		return "member/memberinsert";
	}
	
	@PostMapping("/memberinsert")
	public String register(@Valid MemberVO member, Errors errors) {
		if (errors.hasErrors()) {
			return "member/memberinsert";
		}
		service.register(member);
		return "redirect:/";
	}
	
	//회원강퇴
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/adminRemove")
	public String adminRemove(String userId) {
		service.remove(userId);
		return "redirect:memberList";
	}
	
	//회원탈퇴
	@PreAuthorize("#userId == principal.username")
	@PostMapping("/memberRemove")
	public String memberRemove(String userId, HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		service.remove(userId);
		httpSession.invalidate();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		return "redirect:/";
	}
	
	//회원페이지
	@GetMapping("/myPage/{userId}")
	public String myPage(@PathVariable String userId,Model model) {
		model.addAttribute("member", service.findById(userId));
		return "member/myPage";
	}
	
	//회원수정
	@GetMapping("memberModify")
	public String modifyForm(MemberVO memberVO) {
		return "member/memberModify";
	}
	
	@PostMapping("memberModify")
	public String modify(@Valid MemberVO memberVO,Errors errors ,RedirectAttributes rttr) {
		if (errors.hasErrors()) {
			return "member/memberModify";
		}
		rttr.addAttribute("userId", memberVO.getUserId());
		service.modify(memberVO);
		return "redirect:myPage/"+memberVO.getUserId();
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
