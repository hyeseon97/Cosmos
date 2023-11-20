package com.ssafy.bicycle.model.dto;

import java.security.Timestamp;

public class Travel {
	private int traval_num;
	private String travel_title;
	private String taavel_memo;
	private Timestamp travelDate;
	private String travel_place1Name;
	private String travel_place1Address;
	private String travel_place2Name;
	private String travel_place2Address;
	private String travel_place3Name;
	private String travel_place3Address;
	private String travel_place4Name;
	private String travel_place4Address;
	private String travel_place5Name;
	private String travel_place5Address;

	public Travel() {
		// TODO Auto-generated constructor stub
	}

	public Travel(int traval_num, String travel_title, String taavel_memo, Timestamp travelDate,
			String travel_place1Name, String travel_place1Address, String travel_place2Name,
			String travel_place2Address, String travel_place3Name, String travel_place3Address,
			String travel_place4Name, String travel_place4Address, String travel_place5Name,
			String travel_place5Address) {
		super();
		this.traval_num = traval_num;
		this.travel_title = travel_title;
		this.taavel_memo = taavel_memo;
		this.travelDate = travelDate;
		this.travel_place1Name = travel_place1Name;
		this.travel_place1Address = travel_place1Address;
		this.travel_place2Name = travel_place2Name;
		this.travel_place2Address = travel_place2Address;
		this.travel_place3Name = travel_place3Name;
		this.travel_place3Address = travel_place3Address;
		this.travel_place4Name = travel_place4Name;
		this.travel_place4Address = travel_place4Address;
		this.travel_place5Name = travel_place5Name;
		this.travel_place5Address = travel_place5Address;
	}

	public int getTraval_num() {
		return traval_num;
	}

	public void setTraval_num(int traval_num) {
		this.traval_num = traval_num;
	}

	public String getTravel_title() {
		return travel_title;
	}

	public void setTravel_title(String travel_title) {
		this.travel_title = travel_title;
	}

	public String getTaavel_memo() {
		return taavel_memo;
	}

	public void setTaavel_memo(String taavel_memo) {
		this.taavel_memo = taavel_memo;
	}

	public Timestamp getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Timestamp travelDate) {
		this.travelDate = travelDate;
	}

	public String getTravel_place1Name() {
		return travel_place1Name;
	}

	public void setTravel_place1Name(String travel_place1Name) {
		this.travel_place1Name = travel_place1Name;
	}

	public String getTravel_place1Address() {
		return travel_place1Address;
	}

	public void setTravel_place1Address(String travel_place1Address) {
		this.travel_place1Address = travel_place1Address;
	}

	public String getTravel_place2Name() {
		return travel_place2Name;
	}

	public void setTravel_place2Name(String travel_place2Name) {
		this.travel_place2Name = travel_place2Name;
	}

	public String getTravel_place2Address() {
		return travel_place2Address;
	}

	public void setTravel_place2Address(String travel_place2Address) {
		this.travel_place2Address = travel_place2Address;
	}

	public String getTravel_place3Name() {
		return travel_place3Name;
	}

	public void setTravel_place3Name(String travel_place3Name) {
		this.travel_place3Name = travel_place3Name;
	}

	public String getTravel_place3Address() {
		return travel_place3Address;
	}

	public void setTravel_place3Address(String travel_place3Address) {
		this.travel_place3Address = travel_place3Address;
	}

	public String getTravel_place4Name() {
		return travel_place4Name;
	}

	public void setTravel_place4Name(String travel_place4Name) {
		this.travel_place4Name = travel_place4Name;
	}

	public String getTravel_place4Address() {
		return travel_place4Address;
	}

	public void setTravel_place4Address(String travel_place4Address) {
		this.travel_place4Address = travel_place4Address;
	}

	public String getTravel_place5Name() {
		return travel_place5Name;
	}

	public void setTravel_place5Name(String travel_place5Name) {
		this.travel_place5Name = travel_place5Name;
	}

	public String getTravel_place5Address() {
		return travel_place5Address;
	}

	public void setTravel_place5Address(String travel_place5Address) {
		this.travel_place5Address = travel_place5Address;
	}

}
