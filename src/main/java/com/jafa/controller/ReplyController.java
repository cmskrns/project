package com.jafa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jafa.model.Criteria;
import com.jafa.model.Reply;
import com.jafa.service.ReplyService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/replies")
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	//등록
	@PostMapping(value = "/new",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody Reply reply){
		int insertCount = service.register(reply);
		return insertCount == 1
				? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글목록
	@GetMapping(value = "/pages/{fno}/{page}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reply>> getList(
			@PathVariable("page") int page, @PathVariable("fno") Long fno){
		Criteria cri = new Criteria(page,10);
		System.out.println(cri);
		return new ResponseEntity<>(service.getList(cri, fno),HttpStatus.OK);
	}
	
	//조회
	@GetMapping(value = "/{rno}",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Reply> get(@PathVariable("rno")Long rno){
		return new ResponseEntity<>(service.get(rno),HttpStatus.OK);
	}
	
	//삭제
	@DeleteMapping(value = "/{rno}",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("rno")Long rno){
		return service.remove(rno)==1 
				? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//수정
	@PutMapping(value = "/{rno}")
	public ResponseEntity<String> modify(
			@RequestBody Reply reply, @PathVariable Long rno){
		reply.setRno(rno);
		return service.modify(reply) == 1
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
