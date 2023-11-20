package com.ssafy.bicycle.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.bicycle.model.dao.UserDao;
import com.ssafy.bicycle.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public User getUserOne(int num) {
		return userDao.selectOne(num);
	}

	@Transactional
	@Override
	public boolean modifyUser(User user) {
		return userDao.updateUser(user) > 0;
	}

	@Transactional
	@Override
	public boolean removeUser(int num) {
		return userDao.deleteUser(num) > 0;
	}

	@Override
	public User login(User user) {
		return userDao.selectOne2(user);
	}
}
