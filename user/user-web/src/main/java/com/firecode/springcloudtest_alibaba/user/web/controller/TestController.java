package com.firecode.springcloudtest_alibaba.user.web.controller;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.firecode.springcloud_alibaba.user.api.domain.Dept;
import com.firecode.springcloud_test.alibaba.user.feign.service.UserServiceClient;

@RestController
public class TestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class); 
	
	private RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(3000)).setReadTimeout(Duration.ofSeconds(5000)).build();
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private RestTemplate restTemplate1;
	
	@Autowired
	private UserServiceClient userService;
	
	
	
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
	 * 使用Feign客户端（注意：这个路径不能和Service服务的路径一致）
	 * @return
	 */
	@GetMapping("/bizServiceName2")
	public String bizServiceList2() {
		LOG.info("正在调用 UserService。。。");
		return userService.getServiceName();
	}
	
	/**
	 * 使用Feign客户端（注意：这个路径不能和Service服务的路径一致）
	 * @return
	 */
	@GetMapping("/test1")
	public String test(@RequestParam("name") String name,@RequestParam("age") Integer age) {
		
		return userService.getTest(name, age);
	}
	
	/**
	 * 使用Feign客户端（注意：这个路径不能和Service服务的路径一致）
	 * @return
	 */
	@GetMapping("/dept1")
	public String test() {
		Dept dept = new Dept();
		dept.setNumber(ThreadLocalRandom.current().nextInt(1000));
		dept.setName(UUID.randomUUID().toString());
		return userService.getDept(dept);
	}
	
	/**
	 * 热点限流
	 * @param name
	 * @return
	 */
	@GetMapping("/hotspot")
	// 标记热点限流资源
	@SentinelResource("hotspot")
	public String hotspot(@RequestParam("name") String name) {
		
		return name+"-"+System.currentTimeMillis();
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
