package com.firecode.springcloudtest_alibaba.circuit.sentinel.hgandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 全局的限流回调（注意：要加 @Component 注解（就是要交给Spring容器管理才生效））
 * 注意：当前类逻辑在 CommonFilter 类调用处理
 * @see com.alibaba.csp.sentinel.adapter.servlet.CommonFilter  
 * @author JIANG
 *
 */
@Component
public class GlobalUrlBlockHandler implements UrlBlockHandler {

	@Override
	public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex)
			throws IOException {
		// 限流了
		if (ex instanceof FlowException) {
		}
		// 降级了
		if (ex instanceof DegradeException) {
		}
		// 热点参数限流
		if (ex instanceof ParamFlowException) {
		}
		// 授权限流
		if (ex instanceof AuthorityException) {
		}
		// 系统规则限流
		if (ex instanceof SystemBlockException) {
		}
		response.setStatus(500);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		try (PrintWriter writer = response.getWriter();) {
			
			new ObjectMapper().writeValue(writer, "限流了（我是全局回调）");
		}
	}
}
