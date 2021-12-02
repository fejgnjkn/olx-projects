package com.olx.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertise;
import com.olx.dto.AdvertiseRequest;
import com.olx.services.AdvertiseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/advertise")
public class AdvertiseController {
	
	@Autowired
	AdvertiseService advertiseService;
	
	
	@PostMapping(value="" , consumes ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Create advertise")
	public Advertise createAdvertise(@RequestBody AdvertiseRequest adv, @RequestHeader("Authorization") String token) {
		return advertiseService.createAdvertise(adv, token);
		
	}
	
	@PutMapping(value="/{id}", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Update advertise")
	public Advertise updateAdvertise(@PathVariable("id") int id, @RequestBody AdvertiseRequest adv, @RequestHeader("Authorization") String token) {
		return advertiseService.updateAdvertise(id, adv, token);
	}
	

	@GetMapping(value="/search/filtercriteria",produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Serach advertisements by filters")
	public List<Advertise> searchAdvertisementByFilters(@RequestParam(name = "searchText", required = false) String searchText,
			@RequestParam(name = "dateCondition", required = false) String dateCondition,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "postedBy", required = false) String postedBy,
			@RequestParam(name = "onDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate onDate,
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fromDate,
			@RequestParam(name = "startIndex", required = false) Integer startIndex,
			@RequestParam(name = "records", required = false) Integer records){
	
		return advertiseService.searchAdvertisementByFilters(searchText, dateCondition, category, postedBy, onDate, fromDate, startIndex, records);
	}
	
	
	@GetMapping(value="/",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Seraches advertisements by a filter")
	public List<Advertise> searchAdvertisementByFilter
	(@RequestParam(name = "searchText", required = true) String searchText){
		return advertiseService.searchAdvertisementByFilter(searchText);
	}
	
	@DeleteMapping(value= "user/advertise/{advertiseId}")
	@ApiOperation(value="Deletes advertise")
	public boolean deleteAdvertise(@PathVariable("advertiseId") int id, @RequestHeader("Authorization") String token) {
		return advertiseService.deleteAdvertise(id, token);
	}
	
	
	@GetMapping(value= "")
	@ApiOperation(value="Get all advertise")
	public List<Advertise> getAllAdvertises(@RequestHeader("Authorization") String token) {
		return advertiseService.getAllAdvertises(token);
	}
	
	@GetMapping(value= "/{advertiseId}")
	@ApiOperation(value="Get advertise by id")
	public Advertise getAdvertise(@PathVariable("advertiseId") int id,@RequestHeader("Authorization") String token) {
		return advertiseService.getAdvertise(id,token);
	}
	
	
	@GetMapping(value= "/categories", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List getCategories() {
		return advertiseService.getAllCategories();
	}
	
}
