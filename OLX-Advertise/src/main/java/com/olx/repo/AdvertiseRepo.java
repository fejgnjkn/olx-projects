package com.olx.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepo extends JpaRepository<AdvertiseEntity, Integer> {
	
	
	@Query("select a from AdvertiseEntity a where a.title like %?1")
	public List<AdvertiseEntity> getAdvertisesByFilterCriteria(String searchText, String dateCondition, Integer categoryId,
			String postedBy, LocalDate onDate);
	
	@Query(value = "SELECT a FROM AdvertiseEntity a WHERE a.title LIKE %:searchText% OR a.description LIKE %:searchText% OR a.category LIKE %:searchText%")
	public List<AdvertiseEntity> findAdvertiseByFilter(String searchText);

}
