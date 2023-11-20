package com.ssafy.bicycle.model.dto;

public class InfoBoard {
	private int ib_num;
	private String ib_title;
	private String ib_content;
	private int ib_viewCnt;
	private int ib_rcm;
	private String ib_regDate;

	public InfoBoard() {
		// TODO Auto-generated constructor stub
	}

	public InfoBoard(String ib_title, String ib_content, int ib_viewCnt, int ib_rem, String ib_regDate) {
		this.ib_title = ib_title;
		this.ib_content = ib_content;
		this.ib_viewCnt = ib_viewCnt;
		this.ib_rcm = ib_rem;
		this.ib_regDate = ib_regDate;
	}

	public int getIb_num() {
		return ib_num;
	}

	public void setIb_num(int ib_num) {
		this.ib_num = ib_num;
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

	public int getIb_viewCnt() {
		return ib_viewCnt;
	}

	public void setIb_viewCnt(int ib_viewCnt) {
		this.ib_viewCnt = ib_viewCnt;
	}

	public int getIb_rcm() {
		return ib_rcm;
	}

	public void setIb_rcm(int ib_rcm) {
		this.ib_rcm = ib_rcm;
	}

	public String getIb_regDate() {
		return ib_regDate;
	}

	public void setIb_regDate(String ib_regDate) {
		this.ib_regDate = ib_regDate;
	}

	@Override
	public String toString() {
		return "InfoBoard [ib_num=" + ib_num + ", ib_title=" + ib_title + ", ib_content=" + ib_content + ", ib_viewCnt="
				+ ib_viewCnt + ", ib_rem=" + ib_rcm + ", ib_regDate=" + ib_regDate + "]";
	}
	
	

}
