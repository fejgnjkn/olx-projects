package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Status model")
public class Status {

	@ApiModelProperty("Status")
	private String status;
	@ApiModelProperty("Status id")
	private int id;

	public Status(int id, String status) {
		super();
		this.status = status;
		this.id = id;
	}

	public Status() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Status [status=" + status + ", id=" + id + "]";
	}

}
