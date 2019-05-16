package com.service.user.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.user.bean.User;
import com.service.user.dao.UserMapper;

@RestController
public class UserController {

	@Autowired
	private UserMapper userDAO;

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			UserDetails user = (UserDetails) principal;
			Collection<? extends GrantedAuthority> collection = user.getAuthorities();
			for(GrantedAuthority c: collection) {
				System.out.println("当前用户是"+user.getUsername()+", 角色是"+c.getAuthority());
			}
		} else {
			System.out.println("Error==================");
		}

		User user = userDAO.findOne(id);
		System.out.println(user.toString());
		return user;

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> findAll() {
		List<User> list = userDAO.findAll();
		System.out.println(list.toString());
		return list;
	}

	@GetMapping("/user-instance")
	public List<ServiceInstance> showInfo() {
		return this.discoveryClient.getInstances("User");
	}
}
