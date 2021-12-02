package com.olx.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.olx.dto.Advertise;
import com.olx.dto.AdvertiseRequest;

public interface AdvertiseService {
	
	public Advertise createAdvertise( AdvertiseRequest advertiseRequest, String token);
	public Advertise updateAdvertise( int id,  AdvertiseRequest advertiseRequest,  String token);
	public List<Advertise> searchAdvertisementByFilters( String searchText,
			 String dateCondition,
			 String category,
			 String postedBy,
			LocalDate onDate,
			LocalDate fromDate, Integer startIndex, Integer records);
	
	public List<Advertise> searchAdvertisementByFilter( String searchText);
	public boolean deleteAdvertise(int id, String token);
	public List<Advertise> getAllAdvertises(String token);
	public Advertise getAdvertise(int id, String token);
	public List getAllCategories();
}
