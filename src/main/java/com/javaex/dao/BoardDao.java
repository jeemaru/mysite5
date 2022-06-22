package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int write(BoardVo boardVo) {
		return sqlSession.insert("board.write", boardVo);
		
	}
	
	public int listDelete(int no) {
		return sqlSession.delete("board.listDelete", no);
	}
	
	public List<BoardVo> getList() {
		return sqlSession.selectList("board.getList");
	}
	
}
