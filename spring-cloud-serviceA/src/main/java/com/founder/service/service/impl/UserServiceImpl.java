package com.founder.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.service.bo.User;
import com.founder.service.dao.UserMapper;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl {

	@Autowired
	private UserMapper userMapper;
	
	//注解事务
	//@Transactional(readOnly=true)
	@Transactional
	public User add(User user) {
		int insert = userMapper.insert(user);
		user.setId(insert);
		//测试异常 事务回滚
		//int b=10/0;
		return user;
	}

}
