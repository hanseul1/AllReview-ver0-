package com.hs.review.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import com.hs.review.dto.Review;

public interface ReviewService {

	/** _id로 특정 리뷰 정보 검색 */
	Review searchById(ObjectId _id);

	/** 제품 번호로 리뷰 리스트 검색 */
	List<Review> searchByProduct(String model);

	/** 전체 리뷰 리스트 검색 */
	List<Review> searchAll();

	/** 카테고리로 리뷰 리스트 검색 */
	List<Review> searchByCategory(String category);
	
	/** 작성자 아이디로 리뷰 리스트 검색 */
	List<Review> searchByWriter(String writer);
	
	/** 리뷰 정보 저장*/
	void insert(Review review);
	
	/** 리뷰 삭제 */
	void delete(String id);
	
	/** 리뷰 수정 */
	void update(Review review);
	
	/** input keyword를 포함하고 있는 리뷰 리스트 검색 */
	List<Review> searchByKeywords(Map<String, Object> data);
}