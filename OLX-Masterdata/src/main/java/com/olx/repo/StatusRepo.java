package com.olx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.StatusEntity;

public interface StatusRepo extends JpaRepository<StatusEntity, Integer> {

	public StatusEntity findById(int id);
	
}
