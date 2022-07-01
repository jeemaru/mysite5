package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value="/galleryList", method= {RequestMethod.GET, RequestMethod.POST})
	public String galleryList() {
		System.out.println("galleryService>galleryList");
		
		return "gallery/list";
	}
	
	@RequestMapping(value="/galleryInsert", method= {RequestMethod.GET, RequestMethod.POST})
	public String galleryInsert(@RequestParam("file") MultipartFile file,
			@RequestParam("content") String content) {
		System.out.println("galleryService>galleryInsert");
		
		galleryService.galleryInsert(file);
		
		
		return "gallery/list";
	}
}
