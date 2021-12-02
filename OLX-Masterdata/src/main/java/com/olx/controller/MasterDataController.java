package com.olx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Category;
import com.olx.dto.CategoryList;
import com.olx.dto.Status;
import com.olx.servises.MasterDataService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/masterdata")
public class MasterDataController {

	@Autowired
	MasterDataService masterDataService;
	
	@GetMapping(value="/advertise/category", produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Get all categories")
	public CategoryList getCategories(){
		return masterDataService.getCategories();
	}
	
	@GetMapping(value="/advertise/category/{id}", produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Get all category by id")
	public String getCategoryName(@PathVariable(value ="id") int id){
		return masterDataService.getCategoryName(id);
	}
	
	@GetMapping(value="/advertise" , produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Get status")
	public List<Status> getStatus() {
		return masterDataService.getStatus();
	}
	
	
	@GetMapping(value="/advertise/status/{id}" , produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Get status by id")
	public String getStatusName(@PathVariable(value = "id") int id) {
		return masterDataService.getStatusValue(id);
	}
}
