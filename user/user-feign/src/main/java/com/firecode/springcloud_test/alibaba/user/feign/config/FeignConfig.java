package com.firecode.springcloud_test.alibaba.user.feign.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;
/**
 * Feign配置类（注意：当前类不能加@Configuration注解，加了就会变成全局的配置，因为有父子上下文扫描的问题）
 * @author JIANG
 */
public class FeignConfig {
	
	/**
	 * 定义Feign否日志级别
	 * @return
	 */
	@Bean
	public Logger.Level level(){
		
		return Logger.Level.FULL;
	}

}
