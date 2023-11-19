package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.InfoComment;

public interface InfoCommentService {
	// 댓글 입력
	int writeInfoComment(InfoComment infoComment);

	// 댓글 조회
	List<InfoComment> getListInfoComment();
}
