package com.olx.dto;

import java.time.LocalDate;

public class AdvertiseRequest {
	
	private int id;
	private String title;
	private int price;
	private String description;
	private int categoryId;
	private int statusId = 1;
	
	public AdvertiseRequest() {
		
	}
	
	public AdvertiseRequest(int id, String title, int price, String description, int categoryId, int statusId) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.categoryId = categoryId;
		this.statusId = statusId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	
	

}
