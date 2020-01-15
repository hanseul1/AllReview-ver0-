package com.hs.review.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.review.dto.Category;
import com.hs.review.service.CategoryService;

@CrossOrigin(origins= {"*"}, maxAge = 6000)
@RestController
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	@GetMapping("/category")
	public ResponseEntity<Map<String, Object>> searchAll(){
		return handleSuccess(service.searchAll());
	}
	
	@PostMapping("/category/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody Category category){
		service.insert(category);
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
