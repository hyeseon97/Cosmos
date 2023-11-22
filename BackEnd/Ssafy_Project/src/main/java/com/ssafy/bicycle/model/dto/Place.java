package com.ssafy.bicycle.model.dto;

import java.util.List;

public class Place {
	private int place_num;
	private int place_travelNum;
	private int place_seq;
	private String place_name;
	private String place_address;
	
	public Place() {
		// TODO Auto-generated constructor stub
	}

	public Place(int place_travelNum, int place_seq, String place_name, String place_address) {
		this.place_travelNum = place_travelNum;
		this.place_seq = place_seq;
		this.place_name = place_name;
		this.place_address = place_address;
	}

	public int getPlace_num() {
		return place_num;
	}

	public void setPlace_num(int place_num) {
		this.place_num = place_num;
	}

	public int getPlace_travelNum() {
		return place_travelNum;
	}

	public void setPlace_travelNum(int place_travelNum) {
		this.place_travelNum = place_travelNum;
	}

	public int getPlace_seq() {
		return place_seq;
	}

	public void setPlace_seq(int place_seq) {
		this.place_seq = place_seq;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getPlace_address() {
		return place_address;
	}

	public void setPlace_address(String place_address) {
		this.place_address = place_address;
	}

	@Override
	public String toString() {
		return "Place [place_num=" + place_num + ", place_travelNum=" + place_travelNum + ", place_seq=" + place_seq
				+ ", place_name=" + place_name + ", place_address=" + place_address + "]";
	}

	
}
