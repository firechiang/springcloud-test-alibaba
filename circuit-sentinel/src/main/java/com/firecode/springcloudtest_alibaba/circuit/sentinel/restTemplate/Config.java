package com.firecode.springcloudtest_alibaba.circuit.sentinel.restTemplate;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;

/**
 * Sentinel整合RestTemplate
 * @author JIANG
 */
@Configuration
public class Config {
	
	/**
	 * 只需要加个@SentinelRestTemplate注解，就整合了Sentinel
	 * 可以直接为restTemplate所访问的地址配置流控
	 * 
	 * blockHandler=限流的时候所调用的函数（注意：这个函数需要是静态的而且参数必须是HttpRequest,byte[],ClientHttpRequestExecution,BlockException。返回值必须是ClientHttpResponse）
	 * fallback=异常或降级后的回调函数（注意：这个函数需要是静态的而且参数必须是HttpRequest,byte[],ClientHttpRequestExecution,BlockException。返回值必须是ClientHttpResponse）
	 * blockHandlerClass=限流的时候所调用的函数所在类
	 * fallbackClass=异常或降级后的回调函数所在类
	 * @return
	 */
	@Bean
	@SentinelRestTemplate(blockHandlerClass = TemplateBlockAndFallbackHandler.class,
	                      fallbackClass = TemplateBlockAndFallbackHandler.class,
	                      blockHandler = "srBlock",
	                      fallback = "fallback")
	public RestTemplate restTemplate() {
		
		return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(5)).setReadTimeout(Duration.ofSeconds(3)).build();
	}
	
	
	

}
