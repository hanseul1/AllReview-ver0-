package com.hs.review.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.hs.review.dto.Review;

public interface ReviewService {

	/** _id로 특정 리뷰 정보 검색 */
	Review getReview(ObjectId _id);

	/** 제품 번호로 리뷰 리스트 검색 */
	List<Review> getReviewsByModel(String model);

	/** 전체 리뷰 리스트 검색 */
	List<Review> getReviews();

	/** 카테고리 번호로 리뷰 리스트 검색 */
	List<Review> getReviewsByCategory(String category);
	
	/** 리뷰 정보 저장*/
	void saveReview(Review review);

}