package com.firecode.springcloudtest_alibaba.user.web.controller;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class); 
	
	private RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(3000)).setReadTimeout(Duration.ofSeconds(5000)).build();
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private RestTemplate restTemplate1;
	
	
	
	@GetMapping("/bizServiceName")
	public String bizServiceList() {
		List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
		String url = instances.stream().map(ServiceInstance::getUri).findFirst().orElseThrow(() -> new RuntimeException("未获取到 User-Service 服务")).toString();
		LOG.info("user-biz 服务地址："+url);
		ResponseEntity<String> response = restTemplate.getForEntity(url+"/serviceName", String.class);
		return response.getBody();
	}
	
	/**
	 * 自动负载均衡（注意：请求地址直接写成服务名称即可）
	 * @return
	 */
	@GetMapping("/bizServiceName1")
	public String bizServiceList1() {
		ResponseEntity<String> response = restTemplate1.getForEntity("http://user-service/serviceName", String.class);
		return response.getBody();
	}
	
	/**
	 * 随机获取服务地址
	 * @return
	 */
	public String randomUrl() {
		List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
		int nextInt = ThreadLocalRandom.current().nextInt(instances.size());
		ServiceInstance serviceInstance = instances.get(nextInt);
		if(null == serviceInstance) {
			throw new RuntimeException("未找到服务user-service");
		}
		return serviceInstance.getUri().toASCIIString();
	}

}
