package com.firecode.springcloudtest_alibaba.circuit.sentinel.hgandler;

import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;

/**
 * 拦截请求修改其资源名称，以达到统一限流某一资源（注意：要加 @Component 注解（就是要交给Spring容器管理才生效））
 * 注意：当前类逻辑在 CommonFilter 类调用处理
 * @see com.alibaba.csp.sentinel.adapter.servlet.CommonFilter  
 * @author JIANG
 */
@Component
public class GlobalCleaner implements UrlCleaner {

	@Override
	public String clean(String originUrl) {
		if("/test".contentEquals(originUrl) || "/test1".contentEquals(originUrl)) {
			// 我们在这个控制台统一为这个资源限流即可
			return "test_test";
		}
		return originUrl;
	}

}
