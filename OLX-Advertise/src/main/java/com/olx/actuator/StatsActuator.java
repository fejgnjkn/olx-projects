package com.olx.actuator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="advertise-stats")
public class StatsActuator {
	
	private static Map<String, Object> map= new HashMap<>();
	
	@PostConstruct
	public void initialize() {
		map.put("no-of-advertises", 24);
		map.put("no-of-active-advertises", 16);
		map.put("no-of-closed-advertises", 12);
		map.put("no-of-open-advertises", 12);
		Map<String , Integer> adverUser= new HashMap<>();
		adverUser.put("Nawaz",12 );
		adverUser.put("azar",11);
		map.put("no-of-advertises-by-username",adverUser);
	}
	
	
	@ReadOperation
	public Map<String, Object> getAdertiseStats(){
		return map;
	}

}
