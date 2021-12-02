package com.olx.servises;

import java.util.List;

import com.olx.dto.Category;
import com.olx.dto.CategoryList;
import com.olx.dto.Status;

public interface MasterDataService {

	public List<Category> getAllCategories();
	public List<Status> getStatus();
	public CategoryList getCategories();
	
	public Category getCategory(int id);
	public Status getStatus(int id);
	public String getCategoryName(int id);
	public String getStatusValue(int id);

}
