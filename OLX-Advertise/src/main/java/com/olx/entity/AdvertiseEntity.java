package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ADVERTISES")
public class AdvertiseEntity {
	
	@Id
	@GeneratedValue
	private int id;
	private String title;
	private int price;
	private String description;
	private String category;
	private String userName;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String status;
	private String postedBy;
	
	
	public AdvertiseEntity() {
		
	}
	
	public AdvertiseEntity(String title, int price, String description, String category, String userName,
			LocalDate createdDate, LocalDate modifiedDate, String status, String postedBy) {
		super();
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.userName = userName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.postedBy = postedBy;
	}
	
	public AdvertiseEntity(int id, String title, int price, String description, String category, String userName,
			LocalDate createdDate, LocalDate modifiedDate, String status,String postedBy) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.userName = userName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.postedBy = postedBy;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	@Override
	public String toString() {
		return "AdvertiseEntity [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description
				+ ", category=" + category + ", userName=" + userName + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", status=" + status + "]";
	}
	
	
	
	
	
	

}
