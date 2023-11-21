package com.ssafy.bicycle.model.service;

import java.util.List;

import com.ssafy.bicycle.model.dto.User;

public interface UserService {
	
	// 회원가입
	int signup(User user);
	
	// 회원조회
	List<User> getUserList();
	
	// 회원 한명 조회
	User getUserOne(String id);
	
	// 회원정보 수정
	boolean modifyUser(User user);
	
	// 회원탈퇴
	boolean removeUser(int num);
	
}
