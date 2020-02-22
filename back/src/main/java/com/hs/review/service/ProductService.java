package com.hs.review.service;

import java.util.List;

import com.hs.review.dto.Product;

public interface ProductService {

	/** 모든 모델 목록 검색 */
	List<Product> searchAll();

	/** 카테고리 id에 해당하는 모델 목록 검색 */
	List<Product> searchByCategory(int category);

	/** 모델 정보 등록 */
	void insert(Product product);

	/** 모델 정보 수정 */
	void update(Product product);

	/** 모델 정보 삭제 */
	void delete(int id);

	/** searching word를 포함하고 있는 모델 목록 검색 */
	List<Product> searchBySearchWord(String word);

}