package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> gallerylist(GalleryVo galleryVo){
		
		return sqlSession.selectList("gallery.gallerylist", galleryVo);
	}
	
	
	public int galleryInsert(GalleryVo GalleryVo) {
		
		return sqlSession.update("gallery.galleryInsert", GalleryVo);
		
	}
	
	
}
