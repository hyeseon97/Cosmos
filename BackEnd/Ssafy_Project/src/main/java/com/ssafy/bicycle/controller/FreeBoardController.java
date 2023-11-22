package com.ssafy.bicycle.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.bicycle.model.dto.FreeBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;
import com.ssafy.bicycle.model.service.FreeBoardService;


@RestController
@RequestMapping("/api")
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardService;
	
	@Autowired
	private ResourceLoader  resourceLoader;

	// 등록
	@PostMapping("/free")
	public ResponseEntity<?> write(@RequestBody FreeBoard freeBoard) {

		freeBoardService.writeBoard(freeBoard);

		return new ResponseEntity<FreeBoard>(freeBoard, HttpStatus.CREATED);
	}

	// 조회 or 검색
	@GetMapping("/free")
	public ResponseEntity<?> list(SearchCondition condition) {
		List<FreeBoard> list = freeBoardService.search(condition);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<FreeBoard>>(list, HttpStatus.OK);
	}

	// 상세조회
	@GetMapping("/free/{num}")
	public ResponseEntity<?> detail(@PathVariable int num) {

		FreeBoard freeBoard = freeBoardService.getFreeOne(num);

		if (freeBoard == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<FreeBoard>(freeBoard, HttpStatus.OK);
	}

	// 수정
	@PutMapping("/free")
	public ResponseEntity<?> update(@RequestBody FreeBoard freeBoard) {
		System.out.println();
		if (freeBoardService.modifyFree(freeBoard)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
	}

	// 삭제
	@DeleteMapping("/free/{num}")
	public ResponseEntity<?> delete(@PathVariable int num) {
		if (freeBoardService.removeFree(num)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
	}

    // 3. 등록
//    @PostMapping("/board")
//    public ResponseEntity<FreeBoard> write(@ModelAttribute FreeBoard freeBoard, @RequestParam(required=false) MultipartFile file) {
//        try {
//           System.out.println(freeBoard);
//           System.out.println(file.getOriginalFilename());
//           
//           if(file != null && file.getSize() > 0) {
//               Resource res = resourceLoader.getResource("classpath:/static/upload");
//               board.setImg(System.currentTimeMillis()+"_"+file.getOriginalFilename());;
//               board.setOrgImg(file.getOriginalFilename());
//               file.transferTo(new File(res.getFile().getCanonicalFile()+"/"+freeBoard.getImg()));
//           }
//            // 나머지 게시글 등록 로직
//            freeBoardService.writeBoard(freeBoard);
//
//            return new ResponseEntity<FreeBoard>(freeBoard, HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
	
}
