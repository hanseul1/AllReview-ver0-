package com.hs.review.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.review.dto.User;
import com.hs.review.service.UserService;
import com.hs.review.util.JwtUtil;
import com.hs.review.util.RestUtil;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/signup")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody User user){
		userService.signup(user);
		
		// 가입완료 하면 jwt 토큰 생성 후 브라우저에 전달
		String token = JwtUtil.CreateToken();
		return RestUtil.handleSuccess(token);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Map<String,Object>> searchUser(@PathVariable String id){
		return RestUtil.handleSuccess(userService.searchUser(id));
	}

	@GetMapping("/user/idcheck/{id}")
	public ResponseEntity<Map<String, Object>> idCheck(@PathVariable String id){
		if(userService.searchUser(id) == null)
			return RestUtil.handleSuccess("ok");
		else return RestUtil.handleSuccess("not ok");
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
		if(userService.login(user) == true) {
			String token = JwtUtil.CreateToken();
			return RestUtil.handleSuccess(token);
		}
		else return RestUtil.handleSuccess("not success");
	}
	
	@PutMapping("/user")
	public ResponseEntity<Map<String, Object>> update(@RequestBody User user){
		userService.updateUser(user);
		return RestUtil.handleSuccess("success");
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable String id){
		userService.deleteUser(id);
		return RestUtil.handleSuccess("success");
	}
}
