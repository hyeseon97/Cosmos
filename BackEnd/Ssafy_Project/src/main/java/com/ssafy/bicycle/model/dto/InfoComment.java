package com.ssafy.bicycle.model.dto;

public class InfoComment {
	private int ic_boardNum;
	private String ic_userId;
	private String ic_userName;
	private String ic_content;
	private String ic_regDate;
	
	public InfoComment() {
		// TODO Auto-generated constructor stub
	}

	public InfoComment(int ic_boardNum, String ic_userId, String ic_userName, String ic_content, String ic_regDate) {
		super();
		this.ic_boardNum = ic_boardNum;
		this.ic_userId = ic_userId;
		this.ic_userName = ic_userName;
		this.ic_content = ic_content;
		this.ic_regDate = ic_regDate;
	}

	public int getIc_boardNum() {
		return ic_boardNum;
	}

	public void setIc_boardNum(int ic_boardNum) {
		this.ic_boardNum = ic_boardNum;
	}

	public String getIc_userId() {
		return ic_userId;
	}

	public void setIc_userId(String ic_userId) {
		this.ic_userId = ic_userId;
	}

	public String getIc_userName() {
		return ic_userName;
	}

	public void setIc_userName(String ic_userName) {
		this.ic_userName = ic_userName;
	}

	public String getIc_content() {
		return ic_content;
	}

	public void setIc_content(String ic_content) {
		this.ic_content = ic_content;
	}

	public String getIc_regDate() {
		return ic_regDate;
	}

	public void setIc_regDate(String ic_regDate) {
		this.ic_regDate = ic_regDate;
	}

	@Override
	public String toString() {
		return "InfoComment [ic_boardNum=" + ic_boardNum + ", ic_userId=" + ic_userId + ", ic_userName=" + ic_userName
				+ ", ic_content=" + ic_content + ", ic_regDate=" + ic_regDate + "]";
	}
	
	
	
}
