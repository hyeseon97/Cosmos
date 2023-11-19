package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.InfoBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;

public interface InfoBoardService {
	
	// 게시물 만들기
	int writeBoard(InfoBoard infoBoard);
	
	// 게시물 조회 or 검색
	List<InfoBoard> search(SearchCondition condition);
	
	// 게시물 하나 조회
	InfoBoard getInfoOne(int num);
	
	// 게시물 수정
	boolean modifyInfo(InfoBoard infoBoard);
	
	// 게시물 삭제
	boolean removeInfo(int num);
}
