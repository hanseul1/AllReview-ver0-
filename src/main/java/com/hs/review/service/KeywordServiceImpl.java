package com.hs.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.review.dao.KeywordDao;
import com.hs.review.dto.Keyword;

@Service
public class KeywordServiceImpl implements KeywordService {
	@Autowired
	private KeywordDao dao;
	
	/** 전체 키워드 리스트 리턴 */
	public List<Keyword> searchAll(){
		return dao.searchAll();
	}
	
	/** 카테고리 번호로 키워드 리스트 검색 */
	public List<Keyword> searchByCategory(int category){
		return dao.searchByCategory(category);
	}
}
