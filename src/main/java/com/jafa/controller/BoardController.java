package com.jafa.controller;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.exception.NotFoundBoardException;
import com.jafa.mapper.BoardMapper;
import com.jafa.model.Board;
import com.jafa.model.BoardAttachVO;
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
	
	//게시물리스트
	@GetMapping("/list/{category}")
	public String boardList(@PathVariable String category, Criteria criteria, Model model) {
		criteria.setCategory(category);
		PageMaker pageMaker = new PageMaker(criteria, mapper.totalCount(criteria));
		model.addAttribute("list", service.getList(criteria));
		model.addAttribute("category" ,criteria.getCategory());
		model.addAttribute("pageMaker",pageMaker);
		return "board/list";
	}
	
	//단일게시물
	@GetMapping("/get")
	public String get(Long fno,Model model,
			@CookieValue(required = false) Cookie viewCount, HttpServletRequest request,
			HttpServletResponse response) {
		boolean isAddCount = false;
		if (viewCount != null) {
			String[] viewed = viewCount.getValue().split("/");
			List<String> viewedList = Arrays.stream(viewed).collect(Collectors.toList());
			if (!viewedList.contains(fno.toString())) {
				viewCount.setValue(viewCount.getValue()+fno+"/");
				response.addCookie(viewCount);
				isAddCount = true;
			}
		}else {
			Cookie cookie = new Cookie("viewCount", fno+"/");
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			isAddCount = true;
		}
		Board read = service.get(fno,false); 
		if (read == null) throw new NotFoundBoardException();
		model.addAttribute("board", service.get(fno,isAddCount));
		return "board/get";
	}
	
	//게시물 수정
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/modify")
	public String modifyForm(Long fno,Board board) {
		Board read = service.get(fno,false);
		if (read == null) throw new NotFoundBoardException();
		return "board/modify";
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/modify")
	public String modify(@Valid Board board,Errors errors ,RedirectAttributes rttr) {
		if (errors.hasErrors()) {
			return "board/modify";
		}
		rttr.addAttribute("category", board.getCategory());
		service.modify(board);
		return "redirect:list/"+ board.getCategory();
	}
	
	//게시글 삭제
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/remove")
	public String remove(Long fno,String category,RedirectAttributes rttr) {
		
		List<BoardAttachVO> attachList = service.getAttachList(fno);
		deleteFiles(attachList);
		rttr.addAttribute("category", category);
		service.remove(fno);
		return "redirect:list/" + category;
	}

	
	//게시글 추가
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/register")
	public String registerForm(Board board) {
		return "board/register";
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/register")
	public String register(@Valid Board board,Errors errors ,RedirectAttributes rttr) {
		if (errors.hasErrors()) {
			return "board/register";
		}
		rttr.addAttribute("category", board.getCategory());
		service.register(board);
		return "redirect:list/" + board.getCategory();
	}
	
	//업로드 수정
	@GetMapping(value = "/getAttachList",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long fno){
		List<BoardAttachVO> attachList = service.getAttachList(fno);
		return new ResponseEntity<List<BoardAttachVO>>(attachList,HttpStatus.OK);
	}
	
	//예외처리
	@ExceptionHandler(NotFoundBoardException.class)
	public String notFoundBoard() {
		System.out.println("예외발생");
		return "errorPage/notFoundBoard";
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if (attachList == null || attachList.size() == 0) {return;}
		
		attachList.forEach(attach ->{
			Path file = Paths.get("C:/Project/"+attach.getUploadPath()+"/"+attach.getUuid()+"_"+attach.getFileName());
			
			try {
				Files.deleteIfExists(file);
				if (Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:/Project/"+attach.getUploadPath()+"/s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.deleteIfExists(thumbNail);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
}
