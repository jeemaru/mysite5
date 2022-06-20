package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int join(UserVo userVo) {
		return sqlSession.insert("user.join", userVo);
	}

	public int getUser(UserVo userVo) {
		return sqlSession.selectOne("user.getUser", userVo);
	}
	
	public UserVo login(UserVo userVo) {
		return sqlSession.selectOne("user.login", userVo);
	}
	
	public int modify(UserVo userVo) {
		return sqlSession.update("user.modify", userVo);
	}
	
}
