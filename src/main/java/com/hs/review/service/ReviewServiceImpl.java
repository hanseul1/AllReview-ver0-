package com.hs.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hs.review.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/** _id로 특정 리뷰 정보 검색 */
	public Review getReview(String _id) {
		return mongoTemplate.findById(_id, Review.class);
	}
	
	/** 제품 번호로 리뷰 리스트 검색 */
	public List<Review> getReviewsByProductNo(int productNo){
		Query query = new Query(new Criteria("productNo").is(productNo));
		return mongoTemplate.find(query, Review.class);
	}
}
