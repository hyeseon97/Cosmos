package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.CourseCommentDao;
import com.ssafy.bicycle.model.dto.CourseComment;

@Service
public class CourseCommentServiceImpl implements CourseCommentService {

	@Autowired
	private CourseCommentDao cousreCommentDao;
	
	@Transactional
	@Override
	public int writeCourseComment(CourseComment courseComment) {
		return cousreCommentDao.insertComment(courseComment);
	}

	@Override
	public List<CourseComment> getListCourseComment(int courseNum) {
		return cousreCommentDao.selectAll(courseNum);
	}
	
}
