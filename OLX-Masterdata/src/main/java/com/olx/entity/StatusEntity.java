package com.olx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADV_STATUS")
public class StatusEntity {

	@Id
	@GeneratedValue
	private int id;
	private String status;

	public StatusEntity() {
	}

	public StatusEntity(String status) {
		super();
		this.status = status;
	}

	public StatusEntity(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusEntity [id=" + id + ", status=" + status + "]";
	}
	
	

}
