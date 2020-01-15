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
import com.hs.review.util.RestUtil;

@CrossOrigin(origins= {"*"}, maxAge = 6000)
@RestController
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	@GetMapping("/category")
	public ResponseEntity<Map<String, Object>> searchAll(){
		return RestUtil.handleSuccess(service.searchAll());
	}
	
	@PostMapping("/category/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody Category category){
		service.insert(category);
		return RestUtil.handleSuccess("success");
	}
}
