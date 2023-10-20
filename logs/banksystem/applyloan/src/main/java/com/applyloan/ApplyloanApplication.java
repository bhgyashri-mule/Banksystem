package com.applyloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApplyloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplyloanApplication.class, args);
		System.out.println("aaaaa");
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();

	}

}
