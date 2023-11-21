package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.Course;

public interface CourseDao {

	// 등록
	int insertCourse(Course course);

	// 전체 조회
	List<Course> selectAll();

	// 하나 조회
	Course selectOne(int num);

	// 수정
	int updateCourse(Course course);

	// 삭제
	int deleteCourse(int num);

}
