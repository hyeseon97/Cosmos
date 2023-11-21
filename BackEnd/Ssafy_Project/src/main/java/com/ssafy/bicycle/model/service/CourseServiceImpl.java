package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.CourseDao;
import com.ssafy.bicycle.model.dto.Course;
import com.ssafy.bicycle.model.dto.SearchCondition;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Transactional
	@Override
	public int writeCourse(Course course) {
		return courseDao.insertCourse(course);
	}

	@Override
	public List<Course> search(SearchCondition condition) {
		return courseDao.selectAll();
	}

	@Override
	public Course getCourseOne(int num) {
		return courseDao.selectOne(num);
	}

	@Transactional
	@Override
	public boolean modifyCourse(Course course) {
		return courseDao.updateCourse(course) > 0;
	}

	@Transactional
	@Override
	public boolean removeCourse(int num) {
		return courseDao.deleteCourse(num) > 0;
	}

}
