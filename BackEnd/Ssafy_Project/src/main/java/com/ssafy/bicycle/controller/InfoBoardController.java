package com.ssafy.bicycle.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.bicycle.model.dto.Image;
import com.ssafy.bicycle.model.dto.InfoBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;
import com.ssafy.bicycle.model.service.ImageService;
import com.ssafy.bicycle.model.service.InfoBoardService;


@RestController
@RequestMapping("/api")
public class InfoBoardController {

	@Autowired
	private InfoBoardService infoBoardService;

	@Autowired
	private ResourceLoader  resourceLoader;
	
	@Autowired
	private ImageService imageService;
	
	// 등록
	@PostMapping("/info")
	public ResponseEntity<?> write(@RequestBody InfoBoard infoBoard,@RequestParam(required=false) MultipartFile file) {
		try {

			if (file != null && file.getSize() > 0) {
				Resource res = resourceLoader.getResource("classpath:/static/upload"); // 경로
				Image image = new Image();
				image.setImage_type(3);
				image.setImage_boardNum(infoBoard.getIb_num());
				image.setImage_oriName(file.getOriginalFilename());
				image.setImage_saveName(System.currentTimeMillis() + "_" + file.getOriginalFilename());

				file.transferTo(new File(res.getFile().getCanonicalFile() + "/" + image.getImage_saveName()));

				imageService.writeImage(image);

			}

			infoBoardService.writeBoard(infoBoard);

			return new ResponseEntity<InfoBoard>(infoBoard, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		
		HashMap<String,Integer> map = new HashMap<>();
		map.put("type", 3);
		map.put("num",num);
		
		List<Image> list = imageService.getImageList(map);
		infoBoard.setList(list);

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
	@DeleteMapping("/info/{num}")
	public ResponseEntity<?> delete(@PathVariable int num) {
		System.out.println("delete " + num);
		if (infoBoardService.removeInfo(num)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
	}

}
