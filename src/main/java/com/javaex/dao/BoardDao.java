package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public int read(int no) {
		return sqlSession.selectOne("board.read", no);
		
	}

	public List<BoardVo> getBoardList(String keyword){
		List<BoardVo> boardList = sqlSession.selectList("board.getBoardList", keyword);
		return boardList;
	}
	
	public List<BoardVo> getList4(int startRnum, int endRnum){
		System.out.println("BoardDao.getBoardList4");
		//임시로 묶어줄 그릇만들어줌
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		System.out.println(startRnum+"===="+endRnum);
		
		List<BoardVo> getList4 = sqlSession.selectList("board.getBoardList4", map);
		return getList4;
	}
	
	public int selectTotalCnt() {
		int totalCnt = sqlSession.selectOne("board.selectTotalCnt");
		
		return totalCnt;
	}
	
	
}
