package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.FreeCommentDao;
import com.ssafy.bicycle.model.dto.FreeComment;

@Service
public class FreeCommentServiceImpl implements FreeCommentService {

	@Autowired
	private FreeCommentDao freeCommentDao;
	
	@Transactional
	@Override
	public int writeFreeComment(FreeComment freeComment) {
		return freeCommentDao.insertComment(freeComment);
	}

	@Override
	public List<FreeComment> getListFreeComment(int num) {
		return freeCommentDao.selectAll(num);
	}

	
}
