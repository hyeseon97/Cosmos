package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.CourseMap;

public interface CourseMapDao {

	int insertCourseMap(CourseMap courseMap);

	List<CourseMap> selectAll();

	boolean updateCourseMap(CourseMap courseMap);

	boolean updateCourseMap(int num);

	
	
}
