package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("BoardController.list");

		List<BoardVo> getlist = boardService.getList();

		model.addAttribute("getList", getlist);

		return "board/list";
	}

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("BoardController.writeForm");

		return "board/writeForm";
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController.write");

		boardService.write(boardVo);

		return "redirect:list";
	}

	@RequestMapping(value = "/listDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public String listDelete(@RequestParam("no") int no) {
		System.out.println("BoardController.listDelete");

		boardService.listDelete(no);

		return "redirect:list";
	}
	
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no) {
		System.out.println("boardService.read");
		
		boardService.read(no);
		
		
		
		return "board/read";
		
	}

}
