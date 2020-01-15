package com.hs.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.review.dao.CategoryDao;
import com.hs.review.dto.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao dao;
	
	/** 카테고리 목록 조회  */
	public List<Category> searchAll(){
		return dao.searchAll();
	}
	
	/** 카테고리 추가  */
	public void insert(Category category) {
		dao.insert(category);
	}
}
