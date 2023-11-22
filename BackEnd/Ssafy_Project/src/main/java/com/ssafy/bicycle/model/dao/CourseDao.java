package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.Course;

public interface CourseDao {

	int insertCourse(Course course);

	List<Course> selectAll();

	Course selectOne(int num);

	int updateCourse(Course course);

	int deleteCourse(int num);

	void updateViewCnt(int num);
}
