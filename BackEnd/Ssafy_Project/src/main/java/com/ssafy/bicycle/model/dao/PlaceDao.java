package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.Place;

public interface PlaceDao {

	int insertPlace(Place place);

	List<Place> selectAll(int travelNum);


}
