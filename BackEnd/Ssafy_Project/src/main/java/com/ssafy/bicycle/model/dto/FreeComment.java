package com.ssafy.bicycle.model.dto;

public class FreeComment {
	private int fc_boardNum;
	private String fc_userId;
	private String fc_userName;
	private String fc_content;
	private String fc_regdate;
	
	public FreeComment() {
		// TODO Auto-generated constructor stub
	}
	
	public FreeComment(int fc_boardNum, String fc_userId, String fc_userName, String fc_content, String fc_regdate) {
		super();
		this.fc_boardNum = fc_boardNum;
		this.fc_userId = fc_userId;
		this.fc_userName = fc_userName;
		this.fc_content = fc_content;
		this.fc_regdate = fc_regdate;
	}

	public int getFc_boardNum() {
		return fc_boardNum;
	}

	public void setFc_boardNum(int fc_boardNum) {
		this.fc_boardNum = fc_boardNum;
	}

	public String getFc_userId() {
		return fc_userId;
	}

	public void setFc_userId(String fc_userId) {
		this.fc_userId = fc_userId;
	}

	public String getFc_userName() {
		return fc_userName;
	}

	public void setFc_userName(String fc_userName) {
		this.fc_userName = fc_userName;
	}

	public String getFc_content() {
		return fc_content;
	}

	public void setFc_content(String fc_content) {
		this.fc_content = fc_content;
	}

	public String getFc_regdate() {
		return fc_regdate;
	}

	public void setFc_regdate(String fc_regdate) {
		this.fc_regdate = fc_regdate;
	}

	@Override
	public String toString() {
		return "FreeComment [fc_boardNum=" + fc_boardNum + ", fc_userId=" + fc_userId + ", fc_userName=" + fc_userName
				+ ", fc_content=" + fc_content + ", fc_regdate=" + fc_regdate + "]";
	}
	
	
	
	
}
