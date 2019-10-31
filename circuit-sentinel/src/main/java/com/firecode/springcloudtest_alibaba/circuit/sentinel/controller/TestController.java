package com.firecode.springcloudtest_alibaba.circuit.sentinel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.firecode.springcloudtest_alibaba.circuit.sentinel.hgandler.BlockAndFallbackHandler;

@RestController
public class TestController {
	
	@GetMapping("/test")
	// 标记热点限流资源
	@SentinelResource("hotspot")
	public String test() {
		// 手动配置流控规则
		//initRule();
		return "我测试接口";
	}
	@GetMapping("/test1")
	public String test1() {
		// 手动配置流控规则
		//initRule();
		return "我测试接口1";
	}
	
	/**
	 * value=资源名称
	 * blockHandler=限流的时候所调用的函数（注意：这个函数需要是静态的而且要和当前函数的返回值和参数一致，BlockException参数除外但要放在最后面）
	 * fallback=异常或降级后的回调函数（注意：这个函数需要是静态的而且要和原有函数的返回值和参数一致，Throwable参数除外但要放在最后面）
	 * blockHandlerClass=限流的时候所调用的函数所在类
	 * fallbackClass=异常或降级后的回调函数所在类
	 * @param a
	 * @return
	 */
	@SentinelResource(value="sr",
			          blockHandler = "srBlock",
			          fallback = "fallback",
			          blockHandlerClass = BlockAndFallbackHandler.class,
			          fallbackClass = BlockAndFallbackHandler.class
			          /**exceptionsToIgnore = 忽略那些异常,/
			          /**exceptionsToTrace = 那些异常记录错误次数或比例*/)
	@GetMapping("/sr")
	public String sr(@RequestParam(name="a",required = false) String a) {
		if(StringUtils.isEmpty(a)) {
			throw new RuntimeException("测试错误");
		}
		return System.currentTimeMillis()+"-"+a;
	}
	
	/**
	 * 测试使用Sentinel限流保护代码块
	 * @return
	 * @throws BlockException 
	 */
	@GetMapping("/test_sentinel_api")
	public String test_sentinel_api(@RequestParam(name="a",required = false) String a) {
		String resourceName = "test_sentinel_api";
		Entry entry = null;
		try {
			// 定义test_sentinel_api资源要被Sentinel保护（注意：如果要限流还要在控制台配置该资源的限流规则）
			entry = SphU.entry(resourceName);
			if(StringUtils.isEmpty(a)) {
				throw new RuntimeException("测试异常");
			}
			// 被保护的业务逻辑
			return "正常返回";
		// 如果被保护的资源被限流或降级就会抛出BlockException异常	
		} catch(RuntimeException e) {
			// 统计错误，以方便降级（注意：如果不catch这个异常，当发生这个异常时Sentinel是不会统计错误的,因为它默认只统计BlockException和BlockException的子类）
			Tracer.trace(e);
		    return "发生测试异常";
		}catch (BlockException e) {
			return "被限流或降级了";
		}finally {
			if(entry != null) {
				entry.close();
			}
		}
	}
	
	/**
	 * 测试使用Sentinel限流保护代码块，并配置来源
	 * @return
	 * @throws BlockException 
	 */
	@GetMapping("/test_sentinel_api1")
	public String test_sentinel_api1(@RequestParam(name="a",required = false) String a) {
		String resourceName = "test_sentinel_api";
		Entry entry = null;
		try {
			/**
			 * 为资源添加来源以测试来源限流（注意：这个是全局添加，就是其它接口使用了这个资源也会有这个来源）
			 * @param name   资源名称
			 * @param origin 来源
			 */
			ContextUtil.enter(resourceName, "sources-test_sentinel_api");
			// 定义test_sentinel_api资源要被Sentinel保护（注意：如果要限流还要在控制台配置该资源的限流规则）
			entry = SphU.entry(resourceName);
			if(StringUtils.isEmpty(a)) {
				throw new RuntimeException("测试异常");
			}
			// 被保护的业务逻辑
			return "正常返回";
		// 如果被保护的资源被限流或降级就会抛出BlockException异常	
		} catch(RuntimeException e) {
			// 统计错误，以方便降级（注意：如果不catch这个异常，当发生这个异常时Sentinel是不会统计错误的,因为它默认只统计BlockException和BlockException的子类）
			Tracer.trace(e);
		    return "发生测试异常";
		}catch (BlockException e) {
			return "被限流或降级了";
		}finally {
			if(entry != null) {
				entry.close();
			}
			// 关闭来源
			ContextUtil.exit();
		}
	}
	
	
	/**
	 * 手动配置流控规则
	 */
	private void initRule() {
		List<FlowRule> list = new ArrayList<>();
		FlowRule rule = new FlowRule("/test");
		rule.setCount(1);
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setLimitApp("default");
		list.add(rule);
		FlowRuleManager.loadRules(list);
	}

}
