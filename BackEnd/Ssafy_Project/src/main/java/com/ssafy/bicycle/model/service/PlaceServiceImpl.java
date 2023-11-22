package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.PlaceDao;
import com.ssafy.bicycle.model.dto.Place;

@Service
public class PlaceServiceImpl implements PlaceService{

	@Autowired
	private PlaceDao placeDao;
	
	@Transactional
	@Override
	public int writePlace(Place place) {
		return placeDao.insertPlace(place);
	}

	@Override
	public List<Place> getPlaceList(int travelNum) {
		return placeDao.selectAll(travelNum);
	}

}
