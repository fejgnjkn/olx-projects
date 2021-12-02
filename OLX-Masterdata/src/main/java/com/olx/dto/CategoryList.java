package com.olx.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Category list model")
public class CategoryList {

	@ApiModelProperty("List of category")
	private List<Category> categories;

	public CategoryList() {

	}

	public CategoryList(List<Category> categories) {
		super();
		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "CategoryList [categories=" + categories + "]";
	}

}
