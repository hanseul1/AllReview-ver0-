package com.hs.review.service;

import java.util.List;

import com.hs.review.dto.Review;

public interface ReviewService {

	/** _id로 특정 리뷰 정보 검색 */
	Review getReview(String _id);

	/** 제품 번호로 리뷰 리스트 검색 */
	List<Review> getReviewsByProductNo(int productNo);

}