package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	public List<GalleryVo> gallerylist(){
		
		return galleryDao.gallerylist();
	}
	
	
	
	public String galleryInsert(MultipartFile file, String content, int userNo) {
		System.out.println("GalleryService>galleryInsert");
		System.out.println(file.getOriginalFilename());
		
		//저장할곳
		String saveDir = "C:\\javaStudy\\upload";
		
		String orgName = file.getOriginalFilename();
		
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exName);
		
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString().toString()+exName;
		
		String filePath = saveDir +"\\"+ saveName;
		
		long fileSize = file.getSize();
		
		
		GalleryVo GalleryVo = new GalleryVo(userNo, content, filePath, orgName, saveName, fileSize);
		System.out.println(GalleryVo);
		
		galleryDao.galleryInsert(GalleryVo);
		
		
		try {
			byte[] fileData = file.getBytes();
			OutputStream os = new FileOutputStream(filePath); //파일저장경로 지정
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData); // 값써주기
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return saveName;
	}
	
}
