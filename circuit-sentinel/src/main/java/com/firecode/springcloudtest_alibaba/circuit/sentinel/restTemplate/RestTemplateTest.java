package com.firecode.springcloudtest_alibaba.circuit.sentinel.restTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateTest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/rt")
	public String rt() {
		//可以为https://www.baidu.com配置流控，因为RestTemplate整合的Sentinel
		return restTemplate.getForEntity("https://www.baidu.com", String.class).getBody();
	}

}
