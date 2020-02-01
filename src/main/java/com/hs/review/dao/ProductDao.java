package com.hs.review.dao;

import java.util.List;

import com.hs.review.dto.Product;

public interface ProductDao {

	/** 모든 모델 목록 검색 */
	List<Product> selectAll();

	/** 카테고리 id에 해당하는 모델 목록 검색 */
	List<Product> selectByCategory(int category);

	/** 모델 정보 삽입 */
	void insert(Product product);

	/** 모델 정보 수정 */
	void update(Product product);

	/** 모델 id에 해당하는 모델 정보 삭제 */
	void delete(int id);

	/** 검색 word를 포함하고 있는 모델 목록 검색 */
	List<Product> selectByWord(String word);

}