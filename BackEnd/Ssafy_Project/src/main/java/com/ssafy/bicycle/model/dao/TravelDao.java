package com.ssafy.bicycle.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.bicycle.model.dto.SearchCondition;
import com.ssafy.bicycle.model.dto.Travel;

public interface TravelDao {

	// 게시물 작성
	int insertTravel(Travel travel);

	// 게시물 조회
	List<Travel> selectAll(String id);

	// 게시물 한개 조회
	Travel selectOne(int num);

//	// 게시물 수정
//	int updateTravel(Travel travel);

	// 게시물 삭제
	int deleteTravel(HashMap<String, Object> map);


}
