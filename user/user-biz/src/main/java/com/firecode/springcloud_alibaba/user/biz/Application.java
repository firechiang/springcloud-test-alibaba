package com.firecode.springcloud_alibaba.user.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描Mapper接口（注意：使用的是tk包下面的MapperScan）
@MapperScan("com.firecode.springcloud_alibaba.user.biz.mapper")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
