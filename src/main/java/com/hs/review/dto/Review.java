package com.hs.review.dto;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	protected List<MultipartFile> files;
}