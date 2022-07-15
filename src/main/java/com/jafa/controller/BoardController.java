package com.jafa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.mapper.BoardMapper;
import com.jafa.model.Board;
import com.jafa.model.Criteria;
import com.jafa.model.PageMaker;
import com.jafa.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardMapper mapper;

	@Autowired
	BoardService service;
	
	@GetMapping("/list/{category}")
	public String boardList(@PathVariable String category, Criteria criteria, Model model) {
		criteria.setCategory(category);
		PageMaker pageMaker = new PageMaker(criteria, mapper.totalCount(criteria));
		model.addAttribute("list", service.getList(criteria));
		model.addAttribute("category" ,criteria.getCategory());
		model.addAttribute("pageMaker",pageMaker);
		return "board/list";
	}
	
	@GetMapping("/get")
	public String get(Long fno,Model model) {
		model.addAttribute("board", service.get(fno));
		return "board/get";
	}
	
	@GetMapping("/modify")
	public String modifyForm(Board board) {
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(Board board, RedirectAttributes rttr) {
		rttr.addAttribute("category", board.getCategory());
		service.modify(board);
		return "redirect:list/"+ board.getCategory();
	}
	
	@PostMapping("/remove")
	public String remove(Long fno,String category,RedirectAttributes rttr) {
		rttr.addAttribute("category", category);
		service.remove(fno);
		return "redirect:list/" + category;
	}

	@GetMapping("/register")
	public String registerForm() {
		return "board/register";
	}
	@PostMapping("/register")
	public String register(Board board, RedirectAttributes rttr) {
		rttr.addAttribute("category", board.getCategory());
		service.register(board);
		return "redirect:list/" + board.getCategory();
	}
	
}
