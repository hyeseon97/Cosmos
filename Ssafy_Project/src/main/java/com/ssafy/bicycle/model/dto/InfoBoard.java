package com.ssafy.bicycle.model.dto;

public class InfoBoard {
	private String ib_title;
	private String ib_content;
	private String ib_viewCnt;
	private String ib_regDate;
	
	public InfoBoard() {
		// TODO Auto-generated constructor stub
	}

	public InfoBoard(String ib_title, String ib_content, String ib_viewCnt, String ib_regDate) {
		super();
		this.ib_title = ib_title;
		this.ib_content = ib_content;
		this.ib_viewCnt = ib_viewCnt;
		this.ib_regDate = ib_regDate;
	}

	public String getIb_title() {
		return ib_title;
	}

	public void setIb_title(String ib_title) {
		this.ib_title = ib_title;
	}

	public String getIb_content() {
		return ib_content;
	}

	public void setIb_content(String ib_content) {
		this.ib_content = ib_content;
	}

	public String getIb_viewCnt() {
		return ib_viewCnt;
	}

	public void setIb_viewCnt(String ib_viewCnt) {
		this.ib_viewCnt = ib_viewCnt;
	}

	public String getIb_regDate() {
		return ib_regDate;
	}

	public void setIb_regDate(String ib_regDate) {
		this.ib_regDate = ib_regDate;
	}
	
}
