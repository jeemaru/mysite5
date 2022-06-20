package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		return userDao.join(userVo);
	}
	
	public int getUser(UserVo userVo) {
		return userDao.getUser(userVo);
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.login(userVo);
	}
	
	public int modify(UserVo userVo) {
		return userDao.modify(userVo);
	}
}
