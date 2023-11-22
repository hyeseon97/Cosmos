package com.ssafy.bicycle.model.dto;

import java.util.List;

public class Travel {
	private int travel_num;
	private String travel_title;
	private String travel_memo;
	private String travel_travelDate;
	private String travel_userId;
	
	private List<Place> places;

	public Travel() {
		// TODO Auto-generated constructor stub
	}

	public Travel(int travel_num, String travel_title, String travel_memo, String travel_travelDate,
			String travel_userId, List<Place> places) {
		super();
		this.travel_num = travel_num;
		this.travel_title = travel_title;
		this.travel_memo = travel_memo;
		this.travel_travelDate = travel_travelDate;
		this.travel_userId = travel_userId;
		this.places = places;
	}

	public int getTravel_num() {
		return travel_num;
	}

	public void setTravel_num(int travel_num) {
		this.travel_num = travel_num;
	}

	public String getTravel_title() {
		return travel_title;
	}

	public void setTravel_title(String travel_title) {
		this.travel_title = travel_title;
	}

	public String getTravel_memo() {
		return travel_memo;
	}

	public void setTravel_memo(String travel_memo) {
		this.travel_memo = travel_memo;
	}

	public String getTravel_travelDate() {
		return travel_travelDate;
	}

	public void setTravel_travelDate(String travel_travelDate) {
		this.travel_travelDate = travel_travelDate;
	}

	public String getTravel_userId() {
		return travel_userId;
	}

	public void setTravel_userId(String travel_userId) {
		this.travel_userId = travel_userId;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	@Override
	public String toString() {
		return "Travel [travel_num=" + travel_num + ", travel_title=" + travel_title + ", travel_memo=" + travel_memo
				+ ", travel_travelDate=" + travel_travelDate + ", travel_userId=" + travel_userId + ", places=" + places
				+ "]";
	}

}
