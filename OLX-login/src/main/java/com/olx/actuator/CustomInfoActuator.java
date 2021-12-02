package com.olx.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoActuator implements  InfoContributor {

	@Override
	public void contribute(Builder builder) {
		Map<String ,Object> map= new HashMap<String, Object>();
		map.put("total-registered-users", 475);
		map.put("active-login-count", 35);
		map.put("daily sessions", 5);
		builder.withDetails(map);
		
	}

}
