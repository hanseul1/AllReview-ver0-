package com.hs.review.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("review")
public class Review {
	@Id
	private String _id;  							// 리뷰 번호
	private String title;							// 제목
	private String writer; 							// 작성자 아이디
	private String model;  							// 모델명
	private String category;						// 카테고리
	private Date regDate;   						// 작성일
	private Date useDate;   						// 제품 및 서비스 이용 날짜
	private double rating; 							// 평점
	@TextIndexed protected String context;  		// 내용
	private String[] files;							// 이미지 파일 이름
}