package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09User;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);

	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch09/content";
	}

	@PostMapping("/photoload")
	public String photoUpload(Ch09User user) {
		// 문자파트 정보얻기
		String uid = user.getUid();
		String uname = user.getUname();
		String upassword = user.getUpassword();
		logger.info("uid : " + uid);
		logger.info("uname : " + uname);
		logger.info("upassword : " + upassword);
		// 파일파트 정보얻기
		MultipartFile uphoto = user.getUphoto();
		// 파일을 보냈는지 판별
		if (!uphoto.isEmpty()) {
			// 파일의 원래 이름
			String originalFileName = uphoto.getOriginalFilename();
			// 파일 확장자 타입
			String contentType = uphoto.getContentType();
			long size = uphoto.getSize();
			logger.info("originalFileName : " + originalFileName);
			logger.info("contentType : " + contentType);
			logger.info("size : " + size);

			// 파일 저장이름, 중복이 되서는 안된다. 파일 경로 아직은 절대경로만
			String saveDirPath = "D:/MyWorkspace/uploadfiles/";
			String fileName = new Date().getTime() + "-" + originalFileName;
			String filePath = saveDirPath + fileName;
			// 파일객체는 파일경로를 가진 객체
			File file = new File(filePath);
			try {
				uphoto.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/ch09/content";
	}

	@GetMapping("/photolist")
	public String photolist(Model model) {
		// 업로드 경로
		String saveDirPath = "D:/MyWorkspace/uploadfiles/";
		File dir = new File(saveDirPath);
		// 모든 파일 이름을 배열로 저장
		String[] fileNames = dir.list();
		model.addAttribute("fileNames", fileNames);
		return "ch09/photolist";
	}

	@GetMapping("/photodownload")
	/*
	 * 리턴이 없는 void이거나 찾는대상이 없으면 톰캣이 .jsp를 붙여서 찾는다. ?photo=${fileName}
	 *  응답으로 아무것도 없는화면만 나온다. 없으면 404
	 */
	public void PhotoDownload(String photo, HttpServletResponse response) {
		String saveDirPath = "D:/MyWorkspace/uploadfiles/";
		String filePath = saveDirPath + photo;
		
		//응답 본문 내용의 데이터 종류를 응답 헤더에 추가
		response.setContentType("image/jpg");
		
		//응답 본문 데이터를 파일로 다운로드 할 수 있도록 응답 헤더에 추가
		//Http 규약에 따라 헤더에는 ISO-8859-1만 가능해서  변환해야한다.
		try {
			photo = new String(photo.getBytes("UTF-8"), "ISO-8859-1");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		response.setHeader("content-Disposition", "attachment; filename=\"" + photo + "\"");
		
		try {
			OutputStream os = response.getOutputStream();
			InputStream is = new FileInputStream(filePath);
			//buffer까지 쓰면 속도면에서 좋다
			/*byte[] data = new byte[1024];
			while(true) {
				int readByteNum = is.read(data);
				if(readByteNum == -1) {
					break;
				}
				os.write(data, 0, readByteNum);
			}*/
			FileCopyUtils.copy(is, os);
			os.flush();
			os.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
