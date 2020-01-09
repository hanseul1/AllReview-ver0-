package com.hs.review.controller;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.review.dto.Review;
import com.hs.review.service.ReviewService;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/review")
	public ResponseEntity<Map<String, Object>> getReview(@RequestBody ObjectId id){
		return handleSuccess(reviewService.getReview(id));
	}
	
	@GetMapping("/review/model/{model}")
	public ResponseEntity<Map<String,Object>> getReviewsByModel(@PathVariable String model){
		return handleSuccess(reviewService.getReviewsByModel(model));
	}
	
	@GetMapping("/review")
	public ResponseEntity<Map<String,Object>> getReviews(){
		return handleSuccess(reviewService.getReviews());
	}
	
	@PostMapping("/review/save")
	public ResponseEntity<Map<String,Object>> saveReview(@RequestBody Review review){
		reviewService.saveReview(review);
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
