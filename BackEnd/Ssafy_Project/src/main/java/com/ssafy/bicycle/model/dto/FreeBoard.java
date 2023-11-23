package com.ssafy.bicycle.model.dto;

import java.util.List;

/**
 * @author SSAFY
 *
 */
public class FreeBoard {
	private int fb_num;
	private String fb_userId;
	private String fb_title;
	private String fb_writer;
	private String fb_content;
	private int fb_viewCnt;
	private int fb_rcm;
	private String fb_regDate;
	
	private List<Image> list; 
	
	public FreeBoard() {
		// TODO Auto-generated constructor stub
	}

	public FreeBoard(String fb_userId, String fb_title, String fb_writer, String fb_content, List<Image> list) {
		this.fb_userId = fb_userId;
		this.fb_title = fb_title;
		this.fb_writer = fb_writer;
		this.fb_content = fb_content;
		this.list = list;
	}
	
	public int getFb_num() {
		return fb_num;
	}

	public void setFb_num(int fb_num) {
		this.fb_num = fb_num;
	}

	public String getFb_userId() {
		return fb_userId;
	}

	public void setFb_userId(String fb_userId) {
		this.fb_userId = fb_userId;
	}

	public String getFb_title() {
		return fb_title;
	}

	public void setFb_title(String fb_title) {
		this.fb_title = fb_title;
	}

	public String getFb_writer() {
		return fb_writer;
	}

	public void setFb_writer(String fb_writer) {
		this.fb_writer = fb_writer;
	}

	public String getFb_content() {
		return fb_content;
	}

	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}

	public int getFb_viewCnt() {
		return fb_viewCnt;
	}

	public void setFb_viewCnt(int fb_viewCnt) {
		this.fb_viewCnt = fb_viewCnt;
	}

	public int getFb_rcm() {
		return fb_rcm;
	}

	public void setFb_rcm(int fb_rcm) {
		this.fb_rcm = fb_rcm;
	}

	public String getFb_regDate() {
		return fb_regDate;
	}

	public void setFb_regDate(String fb_regDate) {
		this.fb_regDate = fb_regDate;
	}

	public List<Image> getList() {
		return list;
	}

	public void setList(List<Image> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "FreeBoard [fb_num=" + fb_num + ", fb_userId=" + fb_userId + ", fb_title=" + fb_title + ", fb_writer="
				+ fb_writer + ", fb_content=" + fb_content + ", fb_viewCnt=" + fb_viewCnt + ", fb_rcm=" + fb_rcm
				+ ", fb_regDate=" + fb_regDate + ", list=" + list + "]";
	}

	
}
