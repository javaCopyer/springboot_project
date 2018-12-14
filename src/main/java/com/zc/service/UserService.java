package com.zc.service;

import com.zc.dao.UserDao;

public class UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String say(String str) {
		return userDao.say(str);
	}
}
