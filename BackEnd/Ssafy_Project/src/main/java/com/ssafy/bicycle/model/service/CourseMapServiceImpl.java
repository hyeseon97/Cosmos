package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.CourseMapDao;
import com.ssafy.bicycle.model.dto.CourseMap;

@Service
public class CourseMapServiceImpl implements CourseMapService{

	@Autowired
	private CourseMapDao courseMapDao;
	
	@Transactional
	@Override
	public int writeCourseMap(CourseMap courseMap) {
		return courseMapDao.insertCourseMap(courseMap);
	}

	@Override
	public List<CourseMap> getCourseMapList(int courseNum) {
		return courseMapDao.selectAll(courseNum);
	}

	@Transactional
	@Override
	public boolean modifyCourseMap(CourseMap courseMap) {
		return courseMapDao.updateCourseMap(courseMap);
	}

}
