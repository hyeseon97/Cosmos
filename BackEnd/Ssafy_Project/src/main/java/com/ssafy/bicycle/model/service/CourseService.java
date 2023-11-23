package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.Course;
import com.ssafy.bicycle.model.dto.SearchCondition;

public interface CourseService {
	
	// 코스 입력
	int writeCourse(Course course);
	
	// 키워드 조회
	List<Course> geKeywordList(List<String> keywords);
	
	// 코스 조회
	List<Course> search(SearchCondition condition);
	
	// 코스 하나 조회
	Course getCourseOne(int num);
	
	// 코스 수정
	boolean modifyCourse(Course course);
	
	// 코스 삭제
	boolean removeCourse(int num);
}
