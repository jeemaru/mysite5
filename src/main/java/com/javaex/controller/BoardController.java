package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/list4", method = { RequestMethod.GET, RequestMethod.POST })
	public String list4(Model model, @RequestParam(value="crtPage", required=false, defaultValue ="-1") int crtPage) {
		System.out.println("BoardController.list4");
		
		Map<String, Object> pMap = boardService.getList4(crtPage);
		
		model.addAttribute("pMap",pMap);
		
		System.out.println("BoardController--->"+pMap);
		
		return "board/list4";
	}


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
	
	@RequestMapping(value="/search", method= {RequestMethod.GET, RequestMethod.POST})
	public String saerch(@RequestParam("keyword")String keyword, Model model) {
		System.out.println("boardService.keyword");
		System.out.println(keyword);
		
		List<BoardVo> boardList = boardService.getBoardList(keyword);
		
		model.addAllAttributes(boardList);
		
		return "board/list";
		
	}
	
	
	

}
