package com.hs.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.review.dao.ProductDao;
import com.hs.review.dto.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;
	
	/** 모든 모델 목록 검색 */
	public List<Product> searchAll(){
		return dao.selectAll();
	}
	
	/** 카테고리 id에 해당하는 모델 목록 검색 */
	public List<Product> searchByCategory(int category){
		return dao.selectByCategory(category);
	}
	
	/** 모델 정보 등록 */
	public void insertProduct(Product product) {
		dao.insert(product);
	}
	
	/** 모델 정보 수정 */
	public void updateProduct(Product product) {
		dao.update(product);
	}
	
	/** 모델 정보 삭제 */
	public void deleteProduct(int id) {
		dao.delete(id);
	}
	
	/** searching word를 포함하고 있는 모델 목록 검색 */
	public List<Product> searchBySearchWord(String word){
		return dao.selectByWord(word);
	}
}
