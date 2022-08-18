package com.jafa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jafa.mapper.HomeMapper;

@Controller
public class HomeController {
	
	@Autowired
	HomeMapper mapper;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("board",mapper.bestBoardList());
		model.addAttribute("userBoard",mapper.bestUserBoardList());
		return "home";
	}

}
