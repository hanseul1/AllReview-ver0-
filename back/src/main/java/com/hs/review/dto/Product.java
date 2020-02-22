package com.hs.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private int no;			 	// 제품 및 서비스 번호
	protected String name;	 	// 이름
	protected int category;		// 카테고리 번호
	protected int com; 			// 제조(서비스) 회사 번호
}
