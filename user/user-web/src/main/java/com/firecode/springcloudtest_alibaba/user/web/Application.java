package com.firecode.springcloudtest_alibaba.user.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//开启Feign客户端（basePackages=所有@FeignClient注解所在的包路径,FeignConfig.class是Feign全局的配置信息）
@EnableFeignClients(basePackages = "com.firecode.springcloud_test.alibaba.user.feign.service"/**,defaultConfiguration = FeignConfig.class*/)
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
