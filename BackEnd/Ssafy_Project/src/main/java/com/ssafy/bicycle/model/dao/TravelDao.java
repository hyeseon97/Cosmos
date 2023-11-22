package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.FreeBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;

public interface TravelDao {

	// 게시물 작성
	int insertBoard(FreeBoard freeBoard);

	// 게시물 조회
	List<FreeBoard> search(SearchCondition condition);

	// 게시물 한개 조회
	FreeBoard selectOne(int num);

	// 게시물 수정
	int updateBoard(FreeBoard freeBoard);

	// 게시물 삭제
	int deleteBoard(int num);


}
