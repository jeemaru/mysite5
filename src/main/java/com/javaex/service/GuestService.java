package com.javaex.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class GuestService {
	
	@Autowired
	private BoardDao boardDao;
	

}
