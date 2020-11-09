package com.chrisenochdatingsite.Dating.site.entity;

public class SubCategory extends Category {

	private String subCategory;

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	public SubCategory(int id, String category, String subCategory) {
		super(id, category);
		this.subCategory = subCategory;
	}

}
