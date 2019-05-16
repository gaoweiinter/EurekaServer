package com.service.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients
public class MovieFeignApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieFeignApplication.class, args);
	}

}
