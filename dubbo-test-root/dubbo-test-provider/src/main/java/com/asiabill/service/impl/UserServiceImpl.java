package com.asiabill.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;

import com.asiabill.domain.User;
import com.asiabill.service.UserService;

public class UserServiceImpl implements UserService {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public User getUser(String name) {
		logger.info("start getUser(String name), name :" + name);
		User user = new User();
		user.setUserName(name);
		user.setAge("mhb".equals(name) ? "25" : "18");
		user.setTime(new Date());
		return user;
	}

}
