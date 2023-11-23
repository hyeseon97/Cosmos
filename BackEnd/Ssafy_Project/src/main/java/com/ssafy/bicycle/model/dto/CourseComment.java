package com.ssafy.bicycle.model.dto;

public class CourseComment {
	private int cc_num;
	private int cc_courseNum;
	private String cc_userId;
	private String cc_userName;
	private String cc_content;
	private String cc_regDate;
	
	public CourseComment() {
		// TODO Auto-generated constructor stub
	}

	public CourseComment(int cc_courseNum, String cc_userId, String cc_userName, String cc_content,
			String cc_regDate) {
		this.cc_courseNum = cc_courseNum;
		this.cc_userId = cc_userId;
		this.cc_userName = cc_userName;
		this.cc_content = cc_content;
		this.cc_regDate = cc_regDate;
	}

	public int getCc_num() {
		return cc_num;
	}

	public void setCc_num(int cc_num) {
		this.cc_num = cc_num;
	}

	public int getCc_courseNum() {
		return cc_courseNum;
	}

	public void setCc_courseNum(int cc_courseNum) {
		this.cc_courseNum = cc_courseNum;
	}

	public String getCc_userId() {
		return cc_userId;
	}

	public void setCc_userId(String cc_userId) {
		this.cc_userId = cc_userId;
	}

	public String getCc_userName() {
		return cc_userName;
	}

	public void setCc_userName(String cc_userName) {
		this.cc_userName = cc_userName;
	}

	public String getCc_content() {
		return cc_content;
	}

	public void setCc_content(String cc_content) {
		this.cc_content = cc_content;
	}

	public String getCc_regDate() {
		return cc_regDate;
	}

	public void setCc_regDate(String cc_regDate) {
		this.cc_regDate = cc_regDate;
	}

	@Override
	public String toString() {
		return "CourseComment [cc_num=" + cc_num + ", cc_courseNum=" + cc_courseNum + ", cc_userId=" + cc_userId
				+ ", cc_userName=" + cc_userName + ", cc_content=" + cc_content + ", cc_regDate=" + cc_regDate + "]";
	}
	
}
