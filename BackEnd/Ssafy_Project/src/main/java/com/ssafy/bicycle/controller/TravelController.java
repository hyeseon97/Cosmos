package com.ssafy.bicycle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.bicycle.model.dto.Course;
import com.ssafy.bicycle.model.dto.Place;
import com.ssafy.bicycle.model.dto.Travel;
import com.ssafy.bicycle.model.service.PlaceService;
import com.ssafy.bicycle.model.service.TravelService;


@RestController
@RequestMapping("/api")
public class TravelController {

	@Autowired
	private TravelService travelService;
	
	@Autowired
	private PlaceService placeService;

	private int travelNum = 2;
	// 등록
	@PostMapping("/travel")
	public ResponseEntity<?> write(@RequestBody Travel travel) {
		
		int result1 = travelService.writeTravel(travel);
		boolean result2 = true;
		for(int seq = 0;seq<travel.getPlaces().size();seq++) {
			Place p = new Place(travelNum, seq+1, travel.getPlaces().get(seq).getPlace_name(), travel.getPlaces().get(seq).getPlace_address());
			if(placeService.writePlace(p)==0) {
				result2 = false;
			}
		}
		
		travelNum++;
				
//		System.out.println(travel);
//		travelService.writeTravel(travel);
		if(result1==1 && result2) {
			return new ResponseEntity<Travel>(travel,HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

//	// 조회 or 검색
//	@GetMapping("/travel")
//	public ResponseEntity<?> list() {
//		List<Travel> list = travelService.getList();
//		
//		for(int i = 0;i<list.size();i++) {
//			int travelNum = list.get(i).getTravel_num();
//			
//			List<Place> placeList = placeService.getPlaceList(travelNum);
//			
//			list.get(i).setPlaces(placeList);
//		}
//		
//		if (list == null || list.size() == 0) {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//		
//		return new ResponseEntity<List<Travel>>(list, HttpStatus.OK);
//	}

	// 상세조회
	// num을 유저아이디로 하고 1~3개 가져온다음에 인덱스로 디테일을 가자 굿
	@GetMapping("/travel/{id}")
	public ResponseEntity<?> detail(@PathVariable String id) {
		List<Travel> list = travelService.getList(id);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		for(int i = 0;i<list.size();i++) {
			List<Place> placeList = placeService.getPlaceList(list.get(i).getTravel_num());
			list.get(i).setPlaces(placeList);
		}
		return new ResponseEntity<List<Travel>>(list, HttpStatus.OK);
	}

//	// 수정
//	@PutMapping("/travel")
//	public ResponseEntity<?> update(@RequestBody Travel freeBoard) {
//		System.out.println();
//		if (travelService.modifyFree(freeBoard)) {
//			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
//	}

	// 삭제
	// 유저아이디_여행넘버 로 요청 받아오자
	@DeleteMapping("/travel/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		StringTokenizer st = new StringTokenizer(id, "_");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("travel_userId", st.nextToken());
		map.put("travel_num", Integer.parseInt(st.nextToken()));
		if (travelService.removeTravel(map)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
	}

}
