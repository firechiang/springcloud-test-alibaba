package com.firecode.springcloud_alibaba.user.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firecode.springcloud_alibaba.user.api.domain.Dept;
import com.firecode.springcloud_alibaba.user.api.service.UserService;

@RestController
public class TestController implements UserService {
	
	@Autowired
	private Environment environment;
	
	@Autowired(required=false)
	private DiscoveryClient discoveryClient;
	
	/**
	 * 获取当前服务名称
	 * @return
	 */
	@Override
	public String getServiceName() {
		String property = environment.getProperty("spring.application.name");
		return String.join("-",property,String.valueOf(System.currentTimeMillis()));
	}
	
	/**
	 * 获取当前服务所有实例
	 * @return
	 */
	@GetMapping("/serviceList")
	public List<ServiceInstance> getServiceList() {
		String property = environment.getProperty("spring.application.name");
		List<ServiceInstance> instances = discoveryClient.getInstances(property);
		return instances;
	}
	
	@Override
	public String getTest(@RequestParam("name") String name,@RequestParam("age") Integer age) {
		
		return String.join("-",name,age.toString(),String.valueOf(System.currentTimeMillis()));
	}

	//@Override
	@GetMapping("/dept")
	public String getDept(Dept dept) {
		
		return dept.toString();
	}

}
