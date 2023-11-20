package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.InfoCommentDao;
import com.ssafy.bicycle.model.dto.InfoComment;

@Service
public class InfoCommentServiceImpl implements InfoCommentService {

	@Autowired
	private InfoCommentDao infoCommentDao;

	@Transactional
	@Override
	public int writeInfoComment(InfoComment infoComment) {
		return infoCommentDao.insertComment(infoComment);
	}

	@Override
	public List<InfoComment> getListInfoComment() {
		return infoCommentDao.selectAll();
	}

}
