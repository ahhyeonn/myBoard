package egovframework.example.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.example.board.vo.FileVo;

@Controller
public class FileUploadController {
	
	@GetMapping("/uploadAjax.do")
	public void uploadAjax(MultipartFile[] uploadFile) {
		
		System.out.println("=======여기까지 왔나요======");
//		log.info("upload ajax");
		
		String uploadeFolder = "C:\\upload";
		
		for (MultipartFile multipartFile : uploadFile) {
			
			
		String uploadFileName = multipartFile.getOriginalFilename();
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1 );
		File saveFile = new File(uploadeFolder, uploadFileName);
		
		System.out.println("uploadFileName" + uploadFileName);
		
		try {
			multipartFile.transferTo(saveFile);
		} catch (Exception e) {
			System.out.println("에러");
		} //catch끝
		
		} //try 끝
		
		
		
	}
	
	
		
	

}
