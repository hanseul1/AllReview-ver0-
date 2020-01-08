package com.hs.review.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hs.review.service.ReviewService;

@RestController
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/review/getReview/{id}")
	public ResponseEntity<Map<String, Object>> getReview(@PathVariable String id){
		return handleSuccess(reviewService.getReview(id));
	}
	
	@GetMapping("/review/getReviewsByModel/{model}")
	public ResponseEntity<Map<String,Object>> getReviewsByModel(@PathVariable int model){
		return handleSuccess(reviewService.getReviewsByProductNo(model));
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
