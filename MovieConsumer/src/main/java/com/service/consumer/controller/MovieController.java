package com.service.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.service.user.bean.User;

@RestController
public class MovieController {
	@Value("${user.serviceUrl}")
	private String serviceUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		System.out.println("URL: " + serviceUrl);
		return restTemplate.getForObject(this.serviceUrl + id, User.class);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> findAll() {
		return restTemplate.getForObject(serviceUrl, List.class);
	}

	@GetMapping("/user-instance")
	public List<ServiceInstance> showInfo() {
		return this.discoveryClient.getInstances("User");
	}

	@GetMapping("/services")
	public List<String> showServiceInfo() {
		return this.discoveryClient.getServices();
	}

	@GetMapping("/log-user-instance")
	public ServiceInstance logUserInstance() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("EUREKA-CLIENT-A");
		System.out.println("Service: " + serviceInstance.getInstanceId() + " Host: " + serviceInstance.getHost()
				+ " port: " + serviceInstance.getPort());
		return serviceInstance;
	}
}
