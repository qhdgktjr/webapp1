package com.mycompany.webapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class Ch09User {
	private String uid;
	private String uname;
	private String upassword;
	/* private MultipartFile[] uphoto; 다중선택 파일*/
	private MultipartFile uphoto;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public MultipartFile getUphoto() {
		return uphoto;
	}
	public void setUphoto(MultipartFile uphoto) {
		this.uphoto = uphoto;
	}
	

	
}
