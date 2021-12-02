package com.olx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.BlackListUserTokenEntity;

public interface BlackListTokenRepo extends JpaRepository<BlackListUserTokenEntity, String> {
	
	BlackListUserTokenEntity findByToken(String token);

}
