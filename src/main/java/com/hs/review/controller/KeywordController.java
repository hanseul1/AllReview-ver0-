package com.hs.review.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hs.review.service.KeywordService;
import com.hs.review.util.RestUtil;

@RestController
public class KeywordController {
	@Autowired
	private KeywordService service;
	
	@GetMapping("/keyword")
	public ResponseEntity<Map<String,Object>> searchAll(){
		return RestUtil.handleSuccess(service.searchAll());
	}
	
	@GetMapping("/keyword/{category}")
	public ResponseEntity<Map<String,Object>> searchByCategory(@PathVariable int category){
		return RestUtil.handleSuccess(service.searchByCategory(category));
	}
}
