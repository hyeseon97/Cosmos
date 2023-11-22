package com.ssafy.bicycle.model.dto;

import java.util.List;

public class Course {
	private int course_num;
	private String course_userId;
	private String course_name;
	private String course_content;
	private String course_address;
	private String course_keyword;
	private int course_viewCnt;
	private int course_rcm;
	private String course_regDate;

	private List<Double> courseMap;

	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String course_name, String course_content, String course_address, String course_keyword, String course_userId, List<Double> courseMap) {
		super();
		this.course_name = course_name;
		this.course_content = course_content;
		this.course_address = course_address;
		this.course_keyword = course_keyword;
		this.course_userId = course_userId;
		this.courseMap = courseMap;
	}
	
	

	public int getCourse_num() {
		return course_num;
	}

	public void setCourse_num(int course_num) {
		this.course_num = course_num;
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

	public int getCourse_viewCnt() {
		return course_viewCnt;
	}

	public void setCourse_viewCnt(int course_viewCnt) {
		this.course_viewCnt = course_viewCnt;
	}

	public int getCourse_rcm() {
		return course_rcm;
	}

	public void setCourse_rcm(int course_rcm) {
		this.course_rcm = course_rcm;
	}

	public String getCourse_regDate() {
		return course_regDate;
	}

	public void setCourse_regDate(String course_regDate) {
		this.course_regDate = course_regDate;
	}

	public String getCourse_userId() {
		return course_userId;
	}

	public void setCourse_userId(String course_userId) {
		this.course_userId = course_userId;
	}

	public List<Double> getCourseMap() {
		return courseMap;
	}

	public void setCourseMap(List<Double> courseMap) {
		this.courseMap = courseMap;
	}

	@Override
	public String toString() {
		return "Course [course_num=" + course_num + ", course_userId=" + course_userId + ", course_name=" + course_name
				+ ", course_content=" + course_content + ", course_address=" + course_address + ", course_keyword="
				+ course_keyword + ", course_viewCnt=" + course_viewCnt + ", course_rcm=" + course_rcm
				+ ", course_regDate=" + course_regDate + ", courseMap=" + courseMap + "]";
	}

	
	
}
