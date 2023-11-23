package com.ssafy.bicycle.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.bicycle.model.dto.Travel;

public interface TravelService {
	// 게시물 만들기
	int writeTravel(Travel travel);
	
	// 게시물 조회
	List<Travel> getList(String id);
	
	// 게시물 한개 조회
	Travel getTravelOne(int num);
	
//	// 게시물 수정
//	boolean modifyFree(Travel freeBoard);
	
	// 게시물 삭제
	boolean removeTravel(HashMap<String, Object> map);
}
