package com.ssafy.bicycle.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.bicycle.model.dto.InfoBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;
import com.ssafy.bicycle.model.service.InfoBoardService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class InfoBoardController {

	@Autowired
	private InfoBoardService infoBoardService;

	// 등록
	@PostMapping("/info")
	public ResponseEntity<?> write(@RequestBody InfoBoard infoBoard) {

		infoBoardService.writeBoard(infoBoard);

		return new ResponseEntity<InfoBoard>(infoBoard, HttpStatus.CREATED);
	}

	// 조회 or 검색
	@GetMapping("/info")
	public ResponseEntity<?> list(SearchCondition condition) {
		List<InfoBoard> list = infoBoardService.search(condition);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<InfoBoard>>(list, HttpStatus.OK);
	}

	// 상세조회
	@GetMapping("/info/{num}")
	public ResponseEntity<?> detail(@PathVariable int num) {

		InfoBoard infoBoard = infoBoardService.getInfoOne(num);

		if (infoBoard == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<InfoBoard>(infoBoard, HttpStatus.OK);
	}

	// 수정
	@PutMapping("/info")
	public ResponseEntity<?> update(@RequestBody InfoBoard infoBoard) {
		if (infoBoardService.modifyInfo(infoBoard)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
	}

	// 삭제
	@Delete("/info/{num}")
	public ResponseEntity<?> delete(@RequestBody int num) {
		if (infoBoardService.removeInfo(num)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
	}

}
