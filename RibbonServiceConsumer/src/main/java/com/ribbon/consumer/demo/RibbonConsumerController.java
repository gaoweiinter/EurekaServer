package com.ribbon.consumer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonConsumerController {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	LoadBalancerClient loadBalancerClient;

	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
//        return restTemplate.getForEntity("http://EUREKA-CLIENT-A/client", String.class).getBody();
		ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-CLIENT-A");
		String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/client";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForEntity(url, String.class).getBody();
		System.out.println("response:" + response);
		return response;
	}
}
