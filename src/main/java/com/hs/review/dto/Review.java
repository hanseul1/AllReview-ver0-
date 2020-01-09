package com.hs.review.dto;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document("review")
public class Review {
	@Id
	private   ObjectId _id;  						// 리뷰 번호
	protected String title;							// 제목
	protected String writer; 						// 작성자 아이디
	protected String model;  						// 모델명
	protected Date regDate;   						// 작성일
	protected Date useDate;   						// 제품 및 서비스 이용 날짜
	protected double rating; 						// 평점
	protected String context;  						// 내용
	protected Map<String, Object> additions;		// 추가내용
	
	public Review() {}

	public Review(ObjectId _id, String title, String writer, String model, Date regDate, Date useDate, double rating,
			String context, Map<String, Object> additions) {
		super();
		this._id = _id;
		this.title = title;
		this.writer = writer;
		this.model = model;
		this.regDate = regDate;
		this.useDate = useDate;
		this.rating = rating;
		this.context = context;
		this.additions = additions;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Map<String, Object> getAdditions() {
		return additions;
	}

	public void setAdditions(Map<String, Object> additions) {
		this.additions = additions;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}