package com.service.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.service.consumer.UserFeignClient;
import com.service.user.bean.User;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {
	private UserFeignClient userUserFeignClient;
	private UserFeignClient adminUserFeignClient;
	
	private static String serviceUrl = "http://User/";

//	@Autowired
//	private UserFeignClient userFeignClient;
	
	public String getServiceUrl() {
		return serviceUrl;
	}

	@Autowired
	public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
		this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
				.requestInterceptor(new BasicAuthRequestInterceptor("user", "password1")).target(UserFeignClient.class, serviceUrl);
		this.adminUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
				.requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2")).target(UserFeignClient.class, serviceUrl);
	}

	@GetMapping("/user-user/{id}")
	public User findById(@PathVariable Long id) {
//		return userFeignClient.findById(id);
		return userUserFeignClient.findById(id);
	}
	
	@GetMapping("/admin-user/{id}")
	public User findByAdminId(@PathVariable Long id) {
//		return userFeignClient.findById(id);
		return adminUserFeignClient.findById(id);
	}
}
