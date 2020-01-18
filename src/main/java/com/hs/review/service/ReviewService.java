package com.hs.review.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.hs.review.dto.Review;

public interface ReviewService {

	/** _id로 특정 리뷰 정보 검색 */
	Review getReview(ObjectId _id);

	/** 제품 번호로 리뷰 리스트 검색 */
	List<Review> getReviewsByModel(String model);

	/** 전체 리뷰 리스트 검색 */
	List<Review> getReviews();

	/** 카테고리로 리뷰 리스트 검색 */
	List<Review> getReviewsByCategory(String category);
	
	/** 작성자 아이디로 리뷰 리스트 검색 */
	List<Review> getReviewsByWriter(String writer);
	
	/** 리뷰 정보 저장*/
	void saveReview(Review review);
	
	/** 리뷰 삭제 */
	void removeReview(Review review);
	
	/** 리뷰 수정 */
	void updateReview(Review review);
	
	/** input keyword를 포함하고 있는 리뷰 리스트 검색 */
	List<Review> getReviewsByKeywords(Map<String, Object> data);
}