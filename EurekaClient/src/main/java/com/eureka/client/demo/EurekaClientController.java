package com.eureka.client.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientController {
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private ServerConfig serverConfig;

	@GetMapping("/client")
	public String eurekaService() {
		String services = "Current Port: " + serverConfig.getServerPort() + "\r\n  Services: "
				+ discoveryClient.getServices();
		System.out.println(services);
		return services;
	}

}
