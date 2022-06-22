package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public int write(BoardVo boardVo) {
		return boardDao.write(boardVo);
	}
	
	public int listDelete(int no) {
		return boardDao.listDelete(no);
	}
	
	public List<BoardVo> getList() {
		return boardDao.getList();
	}
}
