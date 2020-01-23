package com.hs.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String id;  			// 아이디
	private String pw;  			// 비밀번호
	private String phone;  			// 전화번호
	private String name;  			// 이름
	private String image;			// 프로필 사진 파일 이름
}
