package com.firecode.springcloudtest_alibaba.circuit.sentinel.restTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class TemplateBlockAndFallbackHandler {
	
	/**
	 * 限流后的回调函数（注意：这个函数要和原有函数的返回值和参数一致，BlockException参数除外但要放在最后面）
	 * 注意：要是静态函数
	 * @param a
	 * @param e
	 * @return
	 */
	public static ClientHttpResponse srBlock(HttpRequest request, byte[] bytes, ClientHttpRequestExecution ex1, BlockException ex2) {
		
		return new ClientHttpResponse() {
			
			private ByteArrayInputStream input = new ByteArrayInputStream("限流了...".getBytes(StandardCharsets.UTF_8));

			@Override
			public InputStream getBody() throws IOException {
				
				return input;
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders header = new HttpHeaders();
				header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
				return header;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}

			@Override
			public String getStatusText() throws IOException {
				
				return HttpStatus.OK.name();
			}

			@Override
			public void close() {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	/**
	 * 异常或降级后的回调函数（注意：这个函数要和原有函数的返回值和参数一致，Throwable参数除外但要放在最后面）
	 * 注意：要是静态函数
	 * @param a
	 * @param e
	 * @return
	 */
	public static ClientHttpResponse fallback(HttpRequest r, byte[] bytes, ClientHttpRequestExecution ex1, BlockException ex2) {
		
		return null;
	}

}
