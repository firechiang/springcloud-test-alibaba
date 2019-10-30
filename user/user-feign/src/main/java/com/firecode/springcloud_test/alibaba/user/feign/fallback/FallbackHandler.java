package com.firecode.springcloud_test.alibaba.user.feign.fallback;

import org.springframework.stereotype.Component;

import com.firecode.springcloud_alibaba.user.api.domain.Dept;
import com.firecode.springcloud_test.alibaba.user.feign.service.UserServiceClient;

import feign.hystrix.FallbackFactory;

/**
 * Feign限流回调实现类（注意：这个类必须被Spring容器管理）
 * 不推荐使用，写回调太麻烦，再一个限流还是在服务端自己做比较好
 * @author JIANG
 */
@Component
public class FallbackHandler implements FallbackFactory<UserServiceClient> {

	@Override
	public UserServiceClient create(Throwable cause) {
		
		return new UserServiceClient() {

			@Override
			public String getServiceName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getTest(String name, Integer age) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getDept(Dept dept) {
				cause.printStackTrace();
				return "限流了";
			}
			
		};
	}


}
