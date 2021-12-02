package com.olx.servises;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.dto.CategoryList;
import com.olx.dto.Status;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.exception.InvalidStatusIdException;
import com.olx.repo.CategoryRepo;
import com.olx.repo.StatusRepo;

@Service
public class MasterDataServiceImp implements MasterDataService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	StatusRepo statusRepo;

	@Override
	public List<Category> getAllCategories() {

		List<Category> categoryList = new ArrayList<>();
		List<CategoryEntity> categoryEntities = this.categoryRepo.findAll();
		if (categoryEntities.isEmpty()) {
			return categoryList;
		}

		for (CategoryEntity entity : categoryEntities) {
			categoryList.add(generateCategoryDtoFromEntity(entity));
		}
		return categoryList;
	}

	@Override
	public List<Status> getStatus() {
		List<Status> statusList = new ArrayList<Status>();
		List<StatusEntity> statusEntities = this.statusRepo.findAll();
		if (statusEntities.isEmpty()) {
			return statusList;
		}
		for (StatusEntity entity : statusEntities) {
			statusList.add(generateStatusDtoFromEntity(entity));
		}
		return statusList;
	}

	private Category generateCategoryDtoFromEntity(CategoryEntity categoryEntity) {
		if (categoryEntity == null) {
			return null;
		}
		return new Category(categoryEntity.getId(), categoryEntity.getCategory());

	}

	private CategoryEntity generateCategoryEntityFromDto(Category category) {
		if (category == null) {
			return null;
		}
		return new CategoryEntity(category.getId(), category.getCategory());

	}

	private Status generateStatusDtoFromEntity(StatusEntity statusEntity) {
		if (statusEntity == null) {
			return null;
		}
		return new Status(statusEntity.getId(), statusEntity.getStatus());

	}

	@Override
	public CategoryList getCategories() {
		return new CategoryList(getAllCategories());
	}

	@Override
	public Category getCategory(int id) {
		CategoryEntity categoryEntity = this.categoryRepo.findById(id);
		return generateCategoryDtoFromEntity(categoryEntity);
	}
	
	@Override
	public String getCategoryName(int id) {
		CategoryEntity categoryEntity = this.categoryRepo.findById(id);
		return generateCategoryDtoFromEntity(categoryEntity).getCategory();
	}

	@Override
	public Status getStatus(int id) {
		StatusEntity statusEntity = this.statusRepo.findById(id);
		return generateStatusDtoFromEntity(statusEntity);
	}

	@Override
	public String getStatusValue(int id) {
		StatusEntity statusEntity = this.statusRepo.findById(id);
		return generateStatusDtoFromEntity(statusEntity).getStatus();
	}

}
