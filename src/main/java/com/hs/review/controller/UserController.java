package com.hs.review.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.review.dto.User;
import com.hs.review.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/signup")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody User user){
		userService.signup(user);
		return handleSuccess("success");
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Map<String, Object>> idCheck(@PathVariable String id){
		if(userService.idCheck(id) == true)
			return handleSuccess("ok");
		else return handleSuccess("not ok");
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user, HttpSession session){
		if(userService.login(user) == true) {
			session.setAttribute("id", user.getId());
			return handleSuccess("success");
		}
		else return handleSuccess("not success");
	}
	
	@GetMapping("/user/logout")
	public ResponseEntity<Map<String, Object>> logout(HttpSession session){
		session.invalidate();
		return handleSuccess("success");
	}
	
	@PostMapping("/user/update")
	public ResponseEntity<Map<String, Object>> update(@RequestBody User user){
		userService.updateUser(user);
		return handleSuccess("success");
	}
	
	@PostMapping("/user/delete")
	public ResponseEntity<Map<String, Object>> delete(@RequestBody User user){
		userService.deleteUser(user);
		return handleSuccess("success");
	}
	
	public ResponseEntity<Map<String,Object>> handleSuccess(Object data){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "ok");
		resultMap.put("data", data);
		return new ResponseEntity<Map<String,Object>>(resultMap , HttpStatus.OK);
	}
	
	public ResponseEntity<Map<String,Object>> handleFail(Object data, HttpStatus status){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "fail");
		resultMap.put("data", data);
		return new ResponseEntity<Map<String,Object>>(resultMap , status);
	}
}
