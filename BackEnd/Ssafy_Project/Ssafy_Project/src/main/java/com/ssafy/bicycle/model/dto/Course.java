package com.ssafy.bicycle.model.dto;

import java.security.Timestamp;
import java.util.List;

public class Course {
	private String course_name;
	private String course_content;
	private String course_address;
	private String course_keyword;
	private int int_viewCnt;
	private int course_rcm;
	private Timestamp course_regDate;
	private String course_userId;

	private List<CourseMap> courseMap;

	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String course_name, String course_content, String course_address, String course_keyword,
			int int_viewCnt, int course_rcm, Timestamp course_regDate, String course_userId, List<CourseMap> courseMap) {
		super();
		this.course_name = course_name;
		this.course_content = course_content;
		this.course_address = course_address;
		this.course_keyword = course_keyword;
		this.int_viewCnt = int_viewCnt;
		this.course_rcm = course_rcm;
		this.course_regDate = course_regDate;
		this.course_userId = course_userId;
		this.courseMap = courseMap;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_content() {
		return course_content;
	}

	public void setCourse_content(String course_content) {
		this.course_content = course_content;
	}

	public String getCourse_address() {
		return course_address;
	}

	public void setCourse_address(String course_address) {
		this.course_address = course_address;
	}

	public String getCourse_keyword() {
		return course_keyword;
	}

	public void setCourse_keyword(String course_keyword) {
		this.course_keyword = course_keyword;
	}

	public int getInt_viewCnt() {
		return int_viewCnt;
	}

	public void setInt_viewCnt(int int_viewCnt) {
		this.int_viewCnt = int_viewCnt;
	}

	public int getCourse_rcm() {
		return course_rcm;
	}

	public void setCourse_rcm(int course_rcm) {
		this.course_rcm = course_rcm;
	}

	public Timestamp getCourse_regDate() {
		return course_regDate;
	}

	public void setCourse_regDate(Timestamp course_regDate) {
		this.course_regDate = course_regDate;
	}

	public String getCourse_userId() {
		return course_userId;
	}

	public void setCourse_userId(String course_userId) {
		this.course_userId = course_userId;
	}

	public List<CourseMap> getCourseMap() {
		return courseMap;
	}

	public void setCourseMap(List<CourseMap> courseMap) {
		this.courseMap = courseMap;
	}

}
