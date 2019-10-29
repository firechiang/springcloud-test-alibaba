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
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@RestController
public class TestController {
	
	@GetMapping("/test")
	// 标记热点限流资源
	@SentinelResource("hotspot")
	public String test() {
		// 收订配置流控规则
		initRule();
		return "我测试接口";
	}
	
	/**
	 * 测试使用Sentinel限流保护代码块
	 * @return
	 * @throws BlockException 
	 */
	@GetMapping("/test_sentinel_api")
	public String test_sentinel_api(@RequestParam(name="a",required = false) String a) {
		Entry entry = null;
		try {
			// 定义test_sentinel_api资源要被Sentinel保护（注意：如果要限流还要在控制台配置该资源的限流规则）
			entry = SphU.entry("test_sentinel_api");
			if(StringUtils.isEmpty(a)) {
				throw new RuntimeException("测试异常");
			}
			// 被保护的业务逻辑
			return "正常返回";
			
		// 如果被保护的资源被限流或降级就会抛出BlockException异常	
		} catch(RuntimeException e) {
			// 统计错误，以方便降级（注意：如果不catch这个异常，当发生这个异常时Sentinel是不会统计错误的）
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
