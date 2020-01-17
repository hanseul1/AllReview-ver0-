package com.hs.review.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hs.review.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/** _id로 특정 리뷰 정보 검색 */
	public Review getReview(ObjectId _id) {
		return mongoTemplate.findById(_id, Review.class, "review");
	}
	
	/** 제품 번호로 리뷰 리스트 검색 */
	public List<Review> getReviewsByModel(String model){
		Query query = new Query(new Criteria("model").is(model));
		return mongoTemplate.find(query, Review.class);
	}
	
	/** 전체 리뷰 리스트 검색 */
	public List<Review> getReviews(){
		return mongoTemplate.findAll(Review.class, "review");
	}
	
	/** 카테고리로 리뷰 리스트 검색 */
	public List<Review> getReviewsByCategory(String category){
		Query query = new Query(new Criteria("category").is(category));
		return mongoTemplate.find(query, Review.class);
	}
	
	/** 작성자 아이디로 리뷰 리스트 검색 */
	public List<Review> getReviewsByWriter(String writer){
		Query query = new Query(new Criteria("writer").is(writer));
		return mongoTemplate.find(query, Review.class);
	}
	
	/** 리뷰 정보 저장*/
	public void saveReview(Review review) {
		review.set_id(ObjectId.get());
		mongoTemplate.save(review, "review");
	}
	
	/** 리뷰 삭제 */
	public void removeReview(Review review) {
		mongoTemplate.remove(review);
	}
}
