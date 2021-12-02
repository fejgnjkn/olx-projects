package com.olx.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface MasterDataDelegate {

	
	public List<Map> getAllCategories();
	public List<Map> getAllStatus();
	public String getCategory(int id);
	public String getStatus(int id);

}
