package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.CourseComment;

public interface CourseCommentService {

	// 댓글 작성
	int writeCourseComment(CourseComment courseComment);
	
	// 댓글 조회
	List<CourseComment> getListCourseComment(int courseNum);
	
}
