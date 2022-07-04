package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;


@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value="/galleryList", method= {RequestMethod.GET, RequestMethod.POST})
	public String galleryList(Model model) {
		System.out.println("galleryService>galleryList");
		
		List<GalleryVo> getlist = galleryService.gallerylist();
		
		model.addAttribute("getlist", getlist);
		
		return "gallery/list";
	}
	
	@RequestMapping(value="/galleryInsert", method= {RequestMethod.GET, RequestMethod.POST})
	public String galleryInsert(@RequestParam("file") MultipartFile file,
								@RequestParam("content") String content, 
								@RequestParam("userNo")int userNo){
		System.out.println("galleryService>galleryInsert");
		
		galleryService.galleryInsert(file, content, userNo);
		
		
		return "gallery/list";
	}
}
