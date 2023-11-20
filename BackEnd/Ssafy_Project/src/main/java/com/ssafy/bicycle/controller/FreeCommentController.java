package com.ssafy.bicycle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.bicycle.model.dto.FreeComment;
import com.ssafy.bicycle.model.service.FreeCommentService;

@RestController
@RequestMapping("/api")
public class FreeCommentController {

	@Autowired
	private FreeCommentService freeCommentService;
	
	@PostMapping("/freeComment")
	public ResponseEntity<?> writeFreeComment(@RequestBody FreeComment freeComment){
		freeCommentService.writeFreeComment(freeComment);
		return new ResponseEntity<FreeComment>(freeComment,HttpStatus.CREATED);
	}
	
	@GetMapping("/freeComment/{num}")
	public ResponseEntity<?> list(){
		List<FreeComment> list = freeCommentService.getListFreeComment();
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FreeComment>>(list, HttpStatus.OK);
	}
}
