package com.hs.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.review.dto.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SqlSession session;
	
	/** 모든 모델 목록 검색 */
	public List<Product> selectAll(){
		return session.selectList("sql.product.selectAll");
	}
	
	/** 카테고리 id에 해당하는 모델 목록 검색 */
	public List<Product> selectByCategory(int category){
		return session.selectList("sql.product.selectByCategory", category);
	}
	
	/** 모델 정보 삽입 */
	public void insert(Product product) {
		session.insert("sql.product.insert", product);
	}
	
	/** 모델 정보 수정 */
	public void update(Product product) {
		session.update("sql.product.update", product);
	}
	
	/** 모델 id에 해당하는 모델 정보 삭제 */
	public void delete(int id) {
		session.delete("sql.product.delete", id);
	}
	
	/** 검색 word를 포함하고 있는 모델 목록 검색 */
	public List<Product> selectByWord(String word){
		return session.selectList("sql.product.selectByWord", word);
	}
}
