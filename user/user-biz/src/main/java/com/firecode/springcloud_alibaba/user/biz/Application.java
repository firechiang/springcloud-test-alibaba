package com.firecode.springcloud_alibaba.user.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import tk.mybatis.spring.annotation.MapperScan;

//扫描Mapper接口（注意：使用的是tk包下面的MapperScan）
@MapperScan("com.firecode.springcloud_alibaba.user.biz.mapper")
// 开启注册中心（注意：这个注解可以不用写）
@EnableDiscoveryClient
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
