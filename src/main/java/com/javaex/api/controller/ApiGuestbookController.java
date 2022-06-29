package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	
	@RequestMapping(value="/api/guestbook/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		System.out.println("ApiGuestbookController>addList");
		
		return "apiGuestbook/addList";
		
	}
	
	//데이터만 보내는 방법
	@ResponseBody
	@RequestMapping(value="/api/guestbook/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController>List");
		
		List<GuestbookVo> guestbookList = guestbookService.getGuestList();
		
		System.out.println(guestbookList);
		
		return guestbookList;
	}

	//저장
	@ResponseBody
	@RequestMapping(value="/api/guestbook/add", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController>add");
		
		GuestbookVo gVo = guestbookService.addGuest(guestbookVo);
		System.out.println(gVo);
		
		
		return gVo;
	}
	
	//방명록 삭제
	@ResponseBody
	@RequestMapping(value="/api/guestbook/remove", method = {RequestMethod.GET, RequestMethod.POST})
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.remove");
		System.out.println(guestbookVo);
		
		String state = guestbookService.removeGuest(guestbookVo);
		
		return state;
	}
}
