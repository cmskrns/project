package com.jafa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.exception.NotFoundBoardException;
import com.jafa.mapper.UserBoardMapper;
import com.jafa.model.Criteria;
import com.jafa.model.PageMaker;
import com.jafa.model.UserBoard;
import com.jafa.service.UserBoardService;

import lombok.Getter;

@Controller
@RequestMapping("/userboard")
public class UserBoardController {
	
	@Autowired
	UserBoardService service;
	
	@Autowired
	UserBoardMapper mapper;
	
	@GetMapping("/memberinsert")
	public String memberinsert() {
		
		return "userboard/memberinsert";
	}
	
	@GetMapping("/list/{category}")
	public String userboardList(@PathVariable String category,Criteria criteria,Model model) {
		criteria.setCategory(category);
		PageMaker pageMaker = new PageMaker(criteria, mapper.totalCount(criteria));
		model.addAttribute("list", service.getList(criteria));
		model.addAttribute("category", criteria.getCategory());
		model.addAttribute("pageMaker",pageMaker);
		return "userboard/list";
	}
	
	@GetMapping("/get")
	public String get(Long bno, Model model) {
		UserBoard read = service.get(bno);
		if(read == null) throw new NotFoundBoardException();
		model.addAttribute("board", service.get(bno));
		return "userboard/get";
	}
	
	@GetMapping("/modify")
	public String modifyForm(Long bno, UserBoard userBoard) {
		UserBoard read = service.get(bno);
		if(read == null) throw new NotFoundBoardException();
		return "userboard/modify";
	}
	@PostMapping("/modify")
	public String modify(UserBoard userBoard, RedirectAttributes rttr) {
		rttr.addAttribute("category", userBoard.getCategory());
		service.modify(userBoard);
		return "redirect:list/"+ userBoard.getCategory();
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, String category, RedirectAttributes rttr) {
		rttr.addAttribute("category", category);
		service.remove(bno);
		return "redirect:list/" + category;
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "userboard/register";
	}
	@PostMapping("/register")
	public String register(UserBoard userBoard, RedirectAttributes rttr) {
		rttr.addAttribute("category", userBoard.getCategory());
		service.register(userBoard);
		return "redirect:list/" + userBoard.getCategory();
	}
}
