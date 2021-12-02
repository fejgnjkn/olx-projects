package com.olx.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class MasterDataDelegateImp implements MasterDataDelegate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	@CircuitBreaker(name = "CATEGORY-CIRCUIT-BREAKER", fallbackMethod = "fallbackGetAllCategories")
	public List<Map> getAllCategories() {

		List list = this.restTemplate.getForObject("http://api-gateway/olx/advertise/category", List.class);
		return list;
	}

	@Override
	@CircuitBreaker(name = "STATUS-CIRCUIT-BREAKER", fallbackMethod = "fallbackGetAllStatus")
	public List<Map> getAllStatus() {
		List list = this.restTemplate.getForObject("http://api-gateway/olx/masterdata/advertise", List.class);
		return list;
	}

	public List<Map> fallbackGetAllCategories(Exception ex) {
		System.out.println("Fallback get all categories failed " + ex);
		return null;
	}

	public List<Map> fallbackGetAllStatus(Exception ex) {
		System.out.println("Fallback get all status failed " + ex);
		return null;
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public String getCategory(int id) {
		Map<String, Integer> vars = new HashMap<>();
		vars.put("id", id);
		return this.restTemplate.getForObject("http://api-gateway/olx/masterdata/advertise/category/{id}", String.class,
				vars);
	}

	@Override
	public String getStatus(int id) {
		Map<String, Integer> vars = new HashMap<>();
		vars.put("id", id);
		return this.restTemplate.getForObject("http://api-gateway/olx/masterdata/advertise/status/{id}", String.class,
				vars);
	}

	public List<Map> fallbackGetCategory(int id, Exception ex) {
		System.out.println("Fallback get category failed " + ex);
		return null;
	}

	public List<Map> fallbackGetStatus(int id, Exception ex) {
		System.out.println("Fallback get status failed " + ex);
		return null;
	}
}
