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
		// com.alibaba.csp.sentinel.transport.heartbeat.SimpleHttpHeartbeatSender     将本机的Sentinel服务注册到Sentinel Dashboard（Sentinel Dashboard再调用本机的Sentinel服务获取Sentinel的相关数据）
		// com.alibaba.csp.sentinel.command.CommandHandler                            Sentinel Dashboard访问的Sentinel服务的接口，所有的API都是它的实现
		// com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect         @SentinelResource注解实现类
		// com.alibaba.cloud.sentinel.custom.SentinelBeanPostProcessor                Sentinel和RestTemplate整合实现
		// com.alibaba.cloud.sentinel.feign.SentinelFeign                             Sentinel整合Feign实现
		SpringApplication.run(Application.class, args);
	}
}
