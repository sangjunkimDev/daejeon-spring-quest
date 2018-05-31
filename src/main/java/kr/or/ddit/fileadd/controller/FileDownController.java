package kr.or.ddit.fileadd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/file")
@Controller
public class FileDownController {
	private final String UPLOAD_PATH = "D:/A_TeachingMaterial/7.JspSpring/uploadStorage";
	
	@RequestMapping("/fileDown")
	public void fileDown(@RequestParam String fileName, HttpServletResponse response, HttpServletRequest request) throws IOException {
		File f = new File(UPLOAD_PATH + "/" + fileName);
		
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setContentType("application/octet-stream");
		response.setContentLength((int)f.length());
		
		FileInputStream fis = new FileInputStream(f);
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buffer = new byte[512];
		int len = -1;
		while( (len = fis.read(buffer)) != -1){
			sos.write(buffer, 0, len);
		}
		
		fis.close();
		sos.close();
	}
}
