package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.CourseMap;

public interface CourseMapService{
	
	// 코스맵 등록
	int writeCourseMap(CourseMap courseMap);
	
	// 코스맵 조회
	List<CourseMap> getCourseMapList(int courseNum);
	
	// 코스맵 수정
	boolean modifyCourseMap(CourseMap courseMap);

	
}
