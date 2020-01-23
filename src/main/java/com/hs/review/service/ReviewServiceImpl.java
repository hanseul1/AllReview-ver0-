package com.hs.review.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hs.review.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired private ResourceLoader resourceLoader;

	/** _id로 특정 리뷰 정보 검색 */
	public Review getReview(ObjectId _id) {
		return mongoTemplate.findById(_id, Review.class, "review");
	}
	
	/** 제품 번호로 리뷰 리스트 검색 */
	public List<Review> getReviewsByModel(String model){
		Query query = new Query(new Criteria("model").is(model));
		return mongoTemplate.find(query, Review.class);
	}
	
	/** 전체 리뷰 리스트 검색 */
	public List<Review> getReviews(){
		return mongoTemplate.findAll(Review.class, "review");
	}
	
	/** 카테고리로 리뷰 리스트 검색 */
	public List<Review> getReviewsByCategory(String category){
		Query query = new Query(new Criteria("category").is(category));
		return mongoTemplate.find(query, Review.class);
	}
	
	/** 작성자 아이디로 리뷰 리스트 검색 */
	public List<Review> getReviewsByWriter(String writer){
		Query query = new Query(new Criteria("writer").is(writer));
		return mongoTemplate.find(query, Review.class);
	}
	
	/** 리뷰 정보 저장*/
	public void saveReview(Review review) {
		mongoTemplate.save(review, "review");
	}
	
	/** 리뷰 삭제 */
	public void removeReview(String id) {
		Query query = new Query(new Criteria("_id").is(id));
		mongoTemplate.remove(query, "review");
	}
	
	/** 리뷰 수정 */
	public void updateReview(Review review) {
		Update update = new Update();
		update.set("title", review.getTitle());
		update.set("model", review.getModel());
		update.set("category", review.getCategory());
		update.set("regDate", review.getRegDate());
		update.set("useDate", review.getUseDate());
		update.set("rating", review.getRating());
		update.set("context", review.getContext());
		
		Query query = new Query(new Criteria("_id").is(review.get_id()));
		
		mongoTemplate.updateFirst(query, update, Review.class);
	}
	
	/** input keyword를 포함하고 있는 리뷰 리스트 검색 */
	public List<Review> getReviewsByKeywords(Map<String, Object> data){
		List<String> keywords = (List<String>) data.get("keywords");
		String category = (String) data.get("category");
		
		String[] keywordList = new String[keywords.size()];
		for(int i=0; i<keywordList.length; i++) {
			keywordList[i] = keywords.get(i);
		}
		
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
								.matchingAny(keywordList);
		
		Query query = TextQuery.queryText(criteria);
		
		// 카테고리가 지정되어 있다면 해당 카테고리 조건으로 조회
		if(!category.equals("all"))
			query.addCriteria(new Criteria().andOperator(new Criteria("category").is(category)));
		
		return mongoTemplate.find(query, Review.class);
	}
}
