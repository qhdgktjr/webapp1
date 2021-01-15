package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09Photo;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);

	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileupload(Ch09Photo photo) {
		//문자파트 정보얻기
		String title = photo.getTitle();
		String desc = photo.getDesc();
		logger.info("title : " + title);
		logger.info("desc : " + desc);
		
		//파일타프 정보얻기
		MultipartFile attach = photo.getAttach();
		//파일의 원래 이름
		String originalFileName = attach.getOriginalFilename();
		//파일 확장자 타입
		String contentType = attach.getContentType();
		long size = attach.getSize();
		logger.info("originalFileName : " + originalFileName);
		logger.info("contentType : " + contentType);
		logger.info("size : " + size);
		
		return "redirect:/ch09/content";
		
	}



}
