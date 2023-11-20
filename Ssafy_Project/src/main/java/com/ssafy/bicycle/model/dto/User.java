package com.ssafy.bicycle.model.dto;

import java.security.Timestamp;

public class User {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_birth;
	private String user_grade;
	private int user_boardCnt;
	private int user_commentCnt;
	private String user_joinDate;
	
	public User() {
	}

	public User(String user_id, String user_pw, String user_name, String user_birth) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_grade = "브론즈";
		this.user_boardCnt = 0;
		this.user_commentCnt = 0;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}

	public int getUser_boardCnt() {
		return user_boardCnt;
	}

	public void setUser_boardCnt(int user_boardCnt) {
		this.user_boardCnt = user_boardCnt;
	}

	public int getUser_commentCnt() {
		return user_commentCnt;
	}

	public void setUser_commentCnt(int user_commentCnt) {
		this.user_commentCnt = user_commentCnt;
	}

	public String getUser_joinDate() {
		return user_joinDate;
	}

	public void setUser_joinDate(String user_joinDate) {
		this.user_joinDate = user_joinDate;
	}
}
