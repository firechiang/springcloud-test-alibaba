package com.firecode.springcloud_alibaba.user.biz.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firecode.springcloud_alibaba.user.biz.domain.user.User;
import com.firecode.springcloud_alibaba.user.biz.mapper.user.UserMapper;

@Service
public class UserService {
	
	
	@Autowired
	private UserMapper userMapper;
	
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryAll() {
		
		return userMapper.selectAll();
	}
	
	
	/**
	 * 获取服务的当前时间
	 * @return
	 */
	public String getDateTime(){
		return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
	}

}
