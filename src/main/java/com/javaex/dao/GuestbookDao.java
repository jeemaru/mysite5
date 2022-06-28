package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	

	public List<GuestbookVo> selectList(){
		System.out.println("GuestbookDao>selectList()");
		
		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	//방명록저장
	public int insertGuest(GuestbookVo guestbookVo) {
		System.out.println("sqlSession>insertGuest");
		
		System.out.println("쿼리전-->"+guestbookVo); // no x
		int count = sqlSession.insert("guestbook.insertSelectKey",guestbookVo);
		System.out.println("쿼리후-->"+guestbookVo); // no o
		
		return count;
	}
	
	public GuestbookVo getGuest(int no) {
		System.out.println("sqlSession>getGuest");
		
		GuestbookVo guestbookVo =sqlSession.selectOne("guestbook.getGuest", no);
		
		return guestbookVo;
	}
}
