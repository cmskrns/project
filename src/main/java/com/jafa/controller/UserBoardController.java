package com.jafa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.exception.NotFoundBoardException;
import com.jafa.mapper.UserBoardMapper;
import com.jafa.model.Criteria;
import com.jafa.model.MemberVO;
import com.jafa.model.PageMaker;
import com.jafa.model.UserBoard;
import com.jafa.model.UserBoardAttachVO;
import com.jafa.service.UserBoardService;

import lombok.Getter;

@Controller
@RequestMapping("/userboard")
public class UserBoardController {
	
	@Autowired
	UserBoardService service;
	
	@Autowired
	UserBoardMapper mapper;
	
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
	public String get(Long bno, Model model,
			@CookieValue(required = false) Cookie viewCount, HttpServletRequest request, HttpServletResponse response) {
		boolean isAddCount = false;
		if (viewCount != null) {
			String[] viewed = viewCount.getValue().split("/");
			List<String> viewedList = Arrays.stream(viewed).collect(Collectors.toList());
			if (!viewedList.contains(bno.toString())) {
				viewCount.setValue(viewCount.getValue()+bno+"/");
				response.addCookie(viewCount);
				isAddCount = true;
			}
		}else {
			Cookie cookie = new Cookie("viewCount", bno+"/");
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			isAddCount = true;
		}
		UserBoard read = service.get(bno,false);
		if(read == null) throw new NotFoundBoardException();
		model.addAttribute("board", service.get(bno, isAddCount));
		return "userboard/get";
	}
	
	@GetMapping("/modify")
	public String modifyForm(Long bno, UserBoard userBoard) {
		UserBoard read = service.get(bno, false);
		if(read == null) throw new NotFoundBoardException();
		return "userboard/modify";
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify")
	public String modify(@Valid UserBoard userBoard,Errors errors ,RedirectAttributes rttr) {
		if (errors.hasErrors()) {
			return "userboard/modify";
		}
		rttr.addAttribute("category", userBoard.getCategory());
		service.modify(userBoard);
		return "redirect:list/"+ userBoard.getCategory();
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/remove")
	public String remove(Long bno, String category, RedirectAttributes rttr) {
		List<UserBoardAttachVO> attachList = service.getAttachList(bno);
		deleteFiles(attachList);
		rttr.addAttribute("category", category);
		service.remove(bno);
		return "redirect:list/" + category;
	}
	
	private void deleteFiles(List<UserBoardAttachVO> attachList) {
		if (attachList == null || attachList.size() == 0) {return;}
			
		attachList.forEach(attach ->{
			Path file = Paths.get("C:/Project/User/"+attach.getUploadPath()+"/"+attach.getUuid()+"_"+attach.getFileName());
				
			try {
				Files.deleteIfExists(file);
				if (Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:/Project/User/"+attach.getUploadPath()+"/s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.deleteIfExists(thumbNail);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public String registerForm(UserBoard userBoard) {
		return "userboard/register";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String register(@Valid UserBoard userBoard,Errors errors,RedirectAttributes rttr) {
		if (errors.hasErrors()) {
			return "userboard/register";
		}
		rttr.addAttribute("category", userBoard.getCategory());
		service.register(userBoard);
//		rttr.addAttribute("board", userBoard); 이거 왜 넣었지??..
		return "redirect:list/" + userBoard.getCategory();
	}
	
	@GetMapping(value = "/getAttachList",
			produces =  MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<UserBoardAttachVO>> getAttachList(Long bno){
		List<UserBoardAttachVO> attachList = service.getAttachList(bno);
		return new ResponseEntity<List<UserBoardAttachVO>>(attachList,HttpStatus.OK);
	}
}
