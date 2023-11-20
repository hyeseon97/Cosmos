package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.InfoBoardDao;
import com.ssafy.bicycle.model.dto.InfoBoard;
import com.ssafy.bicycle.model.dto.SearchCondition;

@Service
public class InfoBoardServiceImpl implements InfoBoardService {

	@Autowired
	private InfoBoardDao infoBoardDao;

	@Transactional
	@Override
	public int writeBoard(InfoBoard infoBoard) {
		return infoBoardDao.insertBoard(infoBoard);
	}

	@Override
	public List<InfoBoard> search(SearchCondition condition) {
		return infoBoardDao.selectAll(condition);
	}

	@Override
	public InfoBoard getInfoOne(int num) {
		return infoBoardDao.selectOne(num);
	}

	@Transactional
	@Override
	public boolean modifyInfo(InfoBoard infoBoard) {
		return infoBoardDao.updateBoard(infoBoard) > 0;
	}

	@Transactional
	@Override
	public boolean removeInfo(int num) {
		return infoBoardDao.deleteBoard(num) > 0;
	}

}
