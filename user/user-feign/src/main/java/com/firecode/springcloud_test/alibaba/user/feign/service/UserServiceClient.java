package com.firecode.springcloud_test.alibaba.user.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.firecode.springcloud_alibaba.user.api.domain.Dept;
import com.firecode.springcloud_alibaba.user.api.service.UserService;
import com.firecode.springcloud_test.alibaba.user.feign.fallback.FallbackHandler;

/**
 * name是服务名称,
 * configuration是Feign的相关配置信息（注意：配置类建议使用配置文件的方式配置）
 * fallback是限流或降级后的回调类（注意：这个类必须被Spring容器管理，而且还要实现相关接口）
 * @author JIANG
 */
@FeignClient(name="user-service"/**,configuration = FeignConfig.class*/,fallbackFactory = FallbackHandler.class)
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
