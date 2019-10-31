package com.firecode.springcloudtest_alibaba.circuit.sentinel.hgandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;

/**
 * 限制有某个参数，才能被调用（注意：要加 @Component 注解（就是要交给Spring容器管理才生效））
 * 注意：当前类逻辑在 CommonFilter 类调用处理
 * @see com.alibaba.csp.sentinel.adapter.servlet.CommonFilter  
 * @author JIANG
 */
//@Component
public class GlobalRequestOriginParser implements RequestOriginParser {

	@Override
	public String parseOrigin(HttpServletRequest request) {
		String parameter = request.getParameter("origin");
		if(StringUtils.isEmpty(parameter)) {
			throw new IllegalArgumentException("没有origin参数不允许调用接口");
		}
		return parameter;
	}
}
