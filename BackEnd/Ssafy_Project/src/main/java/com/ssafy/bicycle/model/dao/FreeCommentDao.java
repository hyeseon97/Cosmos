package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.FreeComment;

public interface FreeCommentDao {

	int insertComment(FreeComment freeComment);

	List<FreeComment> selectAll(int boardNum);

}
