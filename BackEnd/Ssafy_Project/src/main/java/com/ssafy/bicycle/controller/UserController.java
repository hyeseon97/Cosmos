package com.ssafy.bicycle.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.bicycle.model.dto.User;
import com.ssafy.bicycle.model.service.UserService;
import com.ssafy.bicycle.util.JwtUtil;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		System.out.println(user);
		int result = userService.signup(user);
		if (result == 0) {
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

	}

	// 관리자 페이지용
	@GetMapping("/user")
	public List<User> list() {
		return userService.getUserList();
	}

	// 유저 한개
	@GetMapping("/user/{id}")
	public ResponseEntity<?> detail(@PathVariable String id){
		User user = userService.getUserOne(id);
		if(user == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	// 마이페이지 > 내 정보 수정
	// 관리자 페이지
	@PutMapping("/user")
	public ResponseEntity<?> update(@RequestBody User user) {
		if (userService.modifyUser(user)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/user/{num}")
	public ResponseEntity<?> delete(@PathVariable int num) {
		if(userService.removeUser(num)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
	}
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();

		User dbUser = userService.getUserOne(user.getUser_id());

		HttpStatus status = null;

		try {
			if (dbUser.getUser_id() != null && dbUser.getUser_id().length() > 0) {
				result.put("login-token", jwtUtil.createToken("id", dbUser.getUser_id()));
				result.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			} else { // 로그인 실패라면
				result.put("message", "FAIL");
				status = HttpStatus.NO_CONTENT;
			}

		} catch (UnsupportedEncodingException e) {
			result.put("message", "FAIL");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@GetMapping("/logout")
	public ResponseEntity<Void> logout() {
//        session.invalidate();

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
