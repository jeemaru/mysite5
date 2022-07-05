package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public int read(int no) {
		return boardDao.read(no);
	}
	
	public List<BoardVo> getBoardList(String keyowrd) {
		return boardDao.getBoardList(keyowrd);
	}
	
	public Map<String, Object> getList4(int crtPage) {
		System.out.println("boardDao.getBoardList4");
		
		//페이지당 글 개수
		int listCnt = 10;
		
		//현재페이지
		crtPage = (crtPage> 0)? crtPage: (crtPage =1);
		if(crtPage>0) {
			
		}else {
			crtPage=1;
		}
		
		//현재페이지 -1 * 리스트
		//시작글 번호
		int startRnum = (crtPage-1) * listCnt + 1;
		//끝번호
		int endRnum = (startRnum + listCnt) -1;

		List<BoardVo> getList4 = boardDao.getList4(startRnum, endRnum);
		
		
		
		//페이징 계산
		
		//전체글 갯수
		int totalCnt = boardDao.selectTotalCnt();
		System.out.println(totalCnt);
		
		//페이지당 버튼 개수
		int pageBtnCount = 5;
		
		//마지막 버튼번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		
		//마지막 번호 번튼
		int startPageBtnNo = (endPageBtnNo - pageBtnCount)+1;
		
		//다음 화살표 유무
		boolean next = false;
		if( (listCnt)*(endPageBtnNo) < totalCnt ) {
			next=true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		boolean prev = false;
		if( startPageBtnNo != 1 ) {
			prev=true;
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("getList4", getList4);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
	}
	

	
	
	/*
	 * for(int i=1 ; i < 127 ; i++) { boardVo.setTitle(i + "번째 게시글(title)입니다.");
	 * boardVo.setContent(i + "번째 게시글(content)입니다."); boardDao.write(boardVo); }
	 * return 1;
	 */
}
