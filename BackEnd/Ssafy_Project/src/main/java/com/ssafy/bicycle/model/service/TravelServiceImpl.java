package com.ssafy.bicycle.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.TravelDao;
import com.ssafy.bicycle.model.dto.Travel;

@Service
public class TravelServiceImpl implements TravelService {

	@Autowired
	private TravelDao travelDao;

	@Transactional
	@Override
	public int writeTravel(Travel travel) {
		return travelDao.insertTravel(travel);
	}

	@Override
	public List<Travel> getList(String id) {
		return travelDao.selectAll(id);
	}

	@Override
	public Travel getTravelOne(int num) {
		return travelDao.selectOne(num);
	}

//	@Transactional
//	@Override
//	public boolean modifyFree(Travel Travel) {
//		return travelDao.updateTravel(Travel) > 0;
//	}

	@Transactional
	@Override
	public boolean removeTravel(HashMap<String, Object> map) {
		return travelDao.deleteTravel(map) > 0;
	}

}
