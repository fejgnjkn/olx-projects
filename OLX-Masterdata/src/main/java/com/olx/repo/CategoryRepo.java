package com.olx.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {

	public CategoryEntity findById(int categoryId);
}
