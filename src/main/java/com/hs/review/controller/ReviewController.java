package com.hs.review.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hs.review.dto.Review;
import com.hs.review.service.FileService;
import com.hs.review.service.MapReduceService;
import com.hs.review.service.ReviewService;
import com.hs.review.util.RestUtil;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FileService fileService;
	@Autowired
	private MapReduceService mapReduceService;
	
	@PostMapping("/review/{id}")
	public ResponseEntity<Map<String, Object>> getReview(@PathVariable ObjectId id){
		return RestUtil.handleSuccess(reviewService.getReview(id));
	}
	
	@GetMapping("/review/model/{model}")
	public ResponseEntity<Map<String,Object>> getReviewsByModel(@PathVariable String model){
		return RestUtil.handleSuccess(reviewService.getReviewsByModel(model));
	}
	
	@GetMapping("/review/all")
	public ResponseEntity<Map<String,Object>> getReviews(){
		return RestUtil.handleSuccess(reviewService.getReviews());
	}
	
	@GetMapping("/review/category/{category}")
	public ResponseEntity<Map<String,Object>> getReviewsByCategory(@PathVariable String category){
		return RestUtil.handleSuccess(reviewService.getReviewsByCategory(category));
	}
	
	@GetMapping("/review/writer/{writer}")
	public ResponseEntity<Map<String,Object>> getReviewsByWriter(@PathVariable String writer){
		return RestUtil.handleSuccess(reviewService.getReviewsByWriter(writer));
	}
	
	@PostMapping("/review/files")
	public ResponseEntity<Map<String,Object>> saveReviewFiles
						(@RequestParam("files") MultipartFile[] files) 
								throws IllegalStateException, IOException{
		return RestUtil.handleSuccess(fileService.insertFiles(files));
	}
	
	@PostMapping("/review")
	public ResponseEntity<Map<String,Object>> save(@RequestBody Review review){
		reviewService.saveReview(review);
		return RestUtil.handleSuccess("success");
	}
	
	@DeleteMapping("/review/{id}")
	public ResponseEntity<Map<String,Object>> remove(@PathVariable String id){
		reviewService.removeReview(id);
		return RestUtil.handleSuccess("success");
	}
	
	@PutMapping("/review")
	public ResponseEntity<Map<String,Object>> update(@RequestBody Review review){
		reviewService.updateReview(review);
		return RestUtil.handleSuccess("success");
	}
	
	@PostMapping("/review/keyword")
	public ResponseEntity<Map<String,Object>> getReviewsByKeywords
									(@RequestBody Map<String, Object> requestData){
		return RestUtil.handleSuccess(reviewService.getReviewsByKeywords(requestData));
	}
	
	@PostMapping("/review/graph/rating")
	public ResponseEntity<Map<String,Object>> getRatingAvgByModel
									(@RequestBody List<String> models){
		return RestUtil.handleSuccess(mapReduceService.getRatingAvgByModel(models));
	}
}
