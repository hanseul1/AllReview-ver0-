package com.hs.review.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hs.review.dto.User;
import com.hs.review.service.FileService;
import com.hs.review.service.UserService;
import com.hs.review.util.JwtUtil;
import com.hs.review.util.JwtUtilImpl;
import com.hs.review.util.RestUtil;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class UserController {
	@Autowired
	private UserService userService;
//	@Autowired
//	private JwtUtil jwtUtil;
	
	@PostMapping("/user/signup")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody User user){
		userService.signup(user);
		
		// 가입완료 하면 jwt 토큰 생성 후 브라우저에 전달
		String token = JwtUtilImpl.CreateToken();
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
			String token = JwtUtilImpl.CreateToken();
			return RestUtil.handleSuccess(token);
		}
		else return RestUtil.handleSuccess("not success");
	}
	
	@PutMapping("/user")
	public ResponseEntity<Map<String, Object>> update(@RequestBody User user){
		userService.updateUser(user);
		return RestUtil.handleSuccess("success");
	}
	
	@PostMapping("/user/delete")
	public ResponseEntity<Map<String, Object>> delete(@RequestBody User user){
		userService.deleteUser(user);
		return RestUtil.handleSuccess("success");
	}
}
