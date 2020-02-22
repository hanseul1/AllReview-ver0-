package com.hs.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.review.dto.Keyword;

@Repository
public class KeywordDaoImpl implements KeywordDao {
	@Autowired
	private SqlSession session;
	
	/** 모든 키워드 리스트 검색 */
	public List<Keyword> selectAll(){
		return session.selectList("sql.keyword.selectAll");
	}
	
	/** 카테고리별 키워드 리스트 검색 */
	public List<Keyword> selectByCategory(String category){
		return session.selectList("sql.keyword.selectByCategory", category);
	}
}
