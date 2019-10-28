package com.firecode.springcloud_test.alibaba.user.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.firecode.springcloud_alibaba.user.api.domain.Dept;
import com.firecode.springcloud_alibaba.user.api.service.UserService;

/**
 * 注意：user-service是服务名称,FeignConfig.class是Feign的相关配置信息（注意：配置类建议使用配置文件的方式配置）
 * @author JIANG
 */
@FeignClient(name="user-service"/**,configuration = FeignConfig.class*/)
public interface UserServiceClient extends UserService {

	/**
	 * Feign GET请求不能直接转换对象，需要添加@SpringQueryMap注解，否则无法调用
	 * @param dept
	 * @return
	 */
	//@Override
	@GetMapping("/dept")
	public String getDept(@SpringQueryMap Dept dept);
}
