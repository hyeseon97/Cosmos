package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.InfoBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;

public interface InfoBoardDao {

	// 게시물 작성
	int insertBoard(InfoBoard infoBoard);

	// 게시물 조회
	List<InfoBoard> selectAll(SearchCondition condition);

	// 게시물 하나 조회
	InfoBoard selectOne(int num);

	// 게시물 수정
	int updateBoard(InfoBoard infoBoard);

	// 게시물 삭제
	int deleteBoard(int num);

}
