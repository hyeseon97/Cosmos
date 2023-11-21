package com.ssafy.bicycle.model.dao;

import java.util.List;

import com.ssafy.bicycle.model.dto.User;

public interface UserDao {
	
	// 회원가입
	int insertUser(User user);
	
	// 전체조회
	List<User> selectAll();
	
	// 한명조회
	User selectOne(String id);
	
	// 정보수정
	int updateUser(User user);

	// 회원 삭제
	int deleteUser(int num);
}
