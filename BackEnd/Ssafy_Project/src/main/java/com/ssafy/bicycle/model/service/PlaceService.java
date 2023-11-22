package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.Place;

public interface PlaceService{
	
	int writePlace(Place place);
	
	List<Place> getPlaceList(int travelNum);
	
}
