package com.ssafy.bicycle.model.dto;

public class CourseMap {
	private int cm_num;
	private int cm_seq;
	private double cm_lat;
	private double cm_lng;
	
	public CourseMap() {
	}

	public CourseMap(int cm_num, int cm_seq, double cm_lat, double cm_lng) {
		super();
		this.cm_num = cm_num;
		this.cm_seq = cm_seq;
		this.cm_lat = cm_lat;
		this.cm_lng = cm_lng;
	}

	public int getCm_num() {
		return cm_num;
	}

	public void setCm_num(int cm_num) {
		this.cm_num = cm_num;
	}

	public int getCm_seq() {
		return cm_seq;
	}

	public void setCm_seq(int cm_seq) {
		this.cm_seq = cm_seq;
	}

	public double getCm_lat() {
		return cm_lat;
	}

	public void setCm_lat(double cm_lat) {
		this.cm_lat = cm_lat;
	}

	public double getCm_lng() {
		return cm_lng;
	}

	public void setCm_lng(double cm_lng) {
		this.cm_lng = cm_lng;
	}
	
}
