package com.hs.review.service;

import java.util.List;

import com.hs.review.dto.Keyword;

public interface KeywordService {

	/** 전체 키워드 리스트 리턴 */
	List<Keyword> searchAll();

	/** 카테고리 번호로 키워드 리스트 검색 */
	List<Keyword> searchByCategory(int category);

}