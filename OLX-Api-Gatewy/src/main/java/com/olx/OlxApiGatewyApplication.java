package com.olx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OlxApiGatewyApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxApiGatewyApplication.class, args);
	}

}
