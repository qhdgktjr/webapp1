package com.mycompany.webapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class Ch09Photo {
	private String title;
	private String desc;
	private MultipartFile attach;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public MultipartFile getAttach() {
		return attach;
	}
	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}
	
}
