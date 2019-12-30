package com.hs.review.dto;

import java.sql.Date;

public class Review {
	private int no;  			// 리뷰 번호
	protected String title;		// 제목
	protected String writer; 	// 작성자 아이디
	protected int productNo;  	// 제품 및 서비스 번호
	protected Date regDate;   	// 작성일
	protected Date useDate;   	// 제품 및 서비스 이용 날짜
	protected double rating; 	// 평점
	protected String context;  	// 내용
	
	public Review() {}

	public Review(String title, String writer, int productNo, Date regDate, Date useDate, double rating,
			String context) {
		this.title = title;
		this.writer = writer;
		this.productNo = productNo;
		this.regDate = regDate;
		this.useDate = useDate;
		this.rating = rating;
		this.context = context;
	}

	public Review(int no, String title, String writer, int productNo, Date regDate, Date useDate, double rating,
			String context) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.productNo = productNo;
		this.regDate = regDate;
		this.useDate = useDate;
		this.rating = rating;
		this.context = context;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
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
