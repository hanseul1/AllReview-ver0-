package com.hs.review.dto;

public class Product {
	private int no;			 	// 제품 및 서비스 번호
	protected String name;	 	// 이름
	protected int category;		// 카테고리 번호
	protected int com; 			// 제조(서비스) 회사 번호
	
	public Product() {}

	public Product(int no, String name, int category, int com) {
		super();
		this.no = no;
		this.name = name;
		this.category = category;
		this.com = com;
	}

	public Product(String name, int category, int com) {
		super();
		this.name = name;
		this.category = category;
		this.com = com;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCom() {
		return com;
	}

	public void setCom(int com) {
		this.com = com;
	}
}
