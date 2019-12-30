package com.hs.review.dto;

public class User {
	private String id;  // 아이디
	private String pw;  // 비밀번호
	private String phone;  // 전화번호
	private String name;  // 이름
	
	public User() {}
	
	public User(String id, String pw, String phone, String name) {
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.name = name;
	}

	public User(String id, String pw, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
