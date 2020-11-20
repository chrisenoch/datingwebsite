package com.chrisenochdatingsite.Dating.site.entity;

public class Category {
	
	private String category;
	private int id;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category(int id, String category) {
		super();
		this.category = category;
		this.id = id;
	}

	public Category(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Category [category=" + category + ", id=" + id + "]";
	}
	
	
	

}
