package com.hs.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.review.dto.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	private SqlSession session;
	
	/** 모든 카테고리 목록 조회 */
	public List<Category> selectAll(){
		return session.selectList("sql.category.selectAll");
	}
	
	/** 카테고리 추가 */
	public void insert(Category category) {
		session.insert("sql.category.insert", category);
	}
}
