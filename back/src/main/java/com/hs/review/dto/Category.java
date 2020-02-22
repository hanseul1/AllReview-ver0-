package com.hs.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	private int no;				// 카테고리 번호
	protected String name;		// 카테고리 이름
}
