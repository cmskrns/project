package com.jafa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jafa.service.UserBoardService;

import lombok.Getter;

@Controller
@RequestMapping("/userboard")
public class UserBoardController {
	
	@Autowired
	UserBoardService service;
	
	@GetMapping("/list/{category}")
	public String userboardList(@PathVariable String category,Model model) {
		
		model.addAttribute("list", service.getList());
		return "userboard/list";
	}
}
