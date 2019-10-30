package com.firecode.springcloudtest_alibaba.circuit.sentinel.hgandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockAndFallbackHandler {
	
	/**
	 * 限流后的回调函数（注意：这个函数要和原有函数的返回值和参数一致，BlockException参数除外但要放在最后面）
	 * 注意：要是静态函数
	 * @param a
	 * @param e
	 * @return
	 */
	public static String srBlock(String a,BlockException e) {
		System.err.println(e.getMessage());
		return "限流了";
	}
	
	/**
	 * 异常或降级后的回调函数（注意：这个函数要和原有函数的返回值和参数一致，Throwable参数除外但要放在最后面）
	 * 注意：要是静态函数
	 * @param a
	 * @param e
	 * @return
	 */
	public static String fallback(String a,Throwable e) {
		System.err.println(e.getMessage());
		return "降级了";
	}

}
