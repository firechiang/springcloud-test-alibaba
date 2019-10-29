package com.firecode.springcloudtest_alibaba.circuit.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		// com.alibaba.csp.sentinel.slots.block.flow.controller.DefaultController     快速失败处理逻辑
		// com.alibaba.csp.sentinel.slots.block.flow.controller.WarmUpController      预热限流处理逻辑
		// com.alibaba.csp.sentinel.slots.block.flow.controller.RateLimiterController 排队等待限流处理逻辑
		// com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule#passCheck         降级逻辑处理
		// com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowChecker#passCheck 热点参数限流逻辑
		SpringApplication.run(Application.class, args);
	}
}
