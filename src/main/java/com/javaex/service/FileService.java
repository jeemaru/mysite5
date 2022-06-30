package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;
import com.javaex.vo.FileVo;

@Service
public class FileService {

	@Autowired
	private FileDao fileDao;
	//파일하드 디스크저장
	//파일정보 추출
	public String save(MultipartFile file) {
		System.out.println("FileService.save");
		System.out.println(file.getOriginalFilename());
		
		//저장할곳 경로
		String saveDir = "C:\\javaStudy\\upload";
		
		// (1) 파일 정보(DB저장)추출 
		
		//오리지날파일명, 저장경로+파일(랜덤)명, 파일사이즈
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf(".")); //숫자위치에서부터 잘라냄 //.이 해당되는 부분을 찾음
		System.out.println(exName);
		
		//String test = UUID.randomUUID().toString(); --> 랜덤성 아이디를 줌
		//long test2 = System.currentTimeMillis(); --> 해당시간의 값을줌
		
		//저장할 파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString().toString()+exName;
	
		//파일경로(디렉토리+저장파일명)
		String filePath = saveDir +"\\"+ saveName;
		
		//파일사이즈
		long fileSize = file.getSize();
		
		//Vo로 묶기
		FileVo fileVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println(fileVo);
		
		
		// -->dao DB저장 -->그림데이터를 문자열로 바꿔서 쿼리문에 들어감 // 테이블도 필요필요
		
		fileDao.save(fileVo);
		
		
		// (2) 파일정보 저장
		
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
