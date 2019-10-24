package com.firecode.springcloudtest_alibaba.user.web.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Bean
	// 自动负载均衡
	@LoadBalanced
	public RestTemplate restTemplate() {
		
		return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(3000)).setReadTimeout(Duration.ofSeconds(5000)).build();
	}
}
