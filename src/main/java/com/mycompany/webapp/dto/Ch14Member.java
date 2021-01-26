package com.mycompany.webapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class Ch14Member {
	
	private String mid;
	private String mname;
	private String mpassword;
	
	
	private MultipartFile mphoto;
	private String mphotosname;
	private String mphotooname;
	private String mphototype;
	
	
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	
	
	
	
	public MultipartFile getMphoto() {
		return mphoto;
	}
	public void setMphoto(MultipartFile mphoto) {
		this.mphoto = mphoto;
	}
	public String getMphotosname() {
		return mphotosname;
	}
	public void setMphotosname(String mphotosname) {
		this.mphotosname = mphotosname;
	}
	public String getMphotooname() {
		return mphotooname;
	}
	public void setMphotooname(String mphotooname) {
		this.mphotooname = mphotooname;
	}
	public String getMphototype() {
		return mphototype;
	}
	public void setMphototype(String mphototype) {
		this.mphototype = mphototype;
	}
	
	
	
}
