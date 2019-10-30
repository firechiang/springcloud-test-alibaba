package com.firecode.springcloud_alibaba.user.api.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.firecode.springcloud_alibaba.user.api.domain.Dept;

@RequestMapping
public interface UserService {
	
	@GetMapping("/serviceName")
	public String getServiceName();
	
	@GetMapping("/test")
	public String getTest(@RequestParam("name") String name,@RequestParam("age") Integer age);
	

}
