package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.InfoComment;

public interface InfoCommentDao {

	// 댓글 작성
	int insertComment(InfoComment infocomment);

	// 댓글 조회
	List<InfoComment> selectAll();

}
