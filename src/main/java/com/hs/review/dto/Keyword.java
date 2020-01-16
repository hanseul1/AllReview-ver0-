package com.hs.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
	private int no;				// 키워드 번호
	protected String word;		// 키워드 텍스트
	protected int category;		// 카테고리 번호
}
