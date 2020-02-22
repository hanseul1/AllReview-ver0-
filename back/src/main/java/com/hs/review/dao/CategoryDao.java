package com.hs.review.dao;

import java.util.List;

import com.hs.review.dto.Category;

public interface CategoryDao {

	/** 모든 카테고리 목록 조회 */
	List<Category> selectAll();

	/** 카테고리 추가 */
	void insert(Category category);

}