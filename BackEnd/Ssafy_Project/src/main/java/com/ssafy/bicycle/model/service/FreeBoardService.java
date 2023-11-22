package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.FreeBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;

public interface FreeBoardService {
	// 게시물 만들기
	int writeBoard(FreeBoard freeBoard);
	
	// 게시물 조회
	List<FreeBoard> search(SearchCondition condition);
	
	// 게시물 한개 조회
	FreeBoard getFreeOne(int num);
	
	// 게시물 수정
	boolean modifyFree(FreeBoard freeBoard);
	
	// 게시물 삭제
	boolean removeFree(int num);
	
	
}
