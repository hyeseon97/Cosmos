package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.FreeBoardDao;
import com.ssafy.bicycle.model.dto.FreeBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardDao freeBoardDao;

	@Transactional
	@Override
	public int writeBoard(FreeBoard freeBoard) {
		return freeBoardDao.insertBoard(freeBoard);
	}

	@Override
	public List<FreeBoard> search(SearchCondition condition) {
		return freeBoardDao.search(condition);
	}

	@Override
	public FreeBoard getFreeOne(int num) {
		return freeBoardDao.selectOne(num);
	}

	@Transactional
	@Override
	public boolean modifyFree(FreeBoard freeBoard) {
		return freeBoardDao.updateBoard(freeBoard) > 0;
	}

	@Transactional
	@Override
	public boolean removeFree(int num) {
		return freeBoardDao.deleteBoard(num) > 0;
	}

}
