package com.service.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.service.user.bean.User;

//@FeignClient(name = "User", configuration = FeignConfiguration.class)
public interface UserFeignClient {

	@GetMapping(value= "/{id}" )
	public User findById(@PathVariable("id") Long id);
}
