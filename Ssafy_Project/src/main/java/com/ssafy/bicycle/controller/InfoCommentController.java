package com.ssafy.bicycle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.bicycle.model.dto.InfoComment;
import com.ssafy.bicycle.model.service.InfoCommentService;

@RestController
@RequestMapping("/api")
public class InfoCommentController {

	@Autowired
	private InfoCommentService infoCommentService;

	@PostMapping("/infoComment")
	public ResponseEntity<?> writeInfoComment(InfoComment infoComment) {
		infoCommentService.writeInfoComment(infoComment);
		return new ResponseEntity<InfoComment>(infoComment, HttpStatus.CREATED);
	}

	@GetMapping("/infoComment/{num}")
	public ResponseEntity<?> list() {
		List<InfoComment> list = infoCommentService.getListInfoComment();
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InfoComment>>(list, HttpStatus.OK);
	}
}
