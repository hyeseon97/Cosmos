package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.CourseComment;

public interface CourseCommentDao {

	int insertComment(CourseComment courseComment);

	List<CourseComment> selectAll(int courseNum);

}
