package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.FreeComment;

public interface FreeCommentService {

	// 댓글 작성
	int writeFreeComment(FreeComment freeComment);
	
	// 댓글 조회
	List<FreeComment> getListFreeComment(int boardNum);
	
}
