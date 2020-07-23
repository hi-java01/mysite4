package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	
	public String restore(MultipartFile file) {
		///////////////데이타 추출//////////////////////////////////
		String saveDir = "C:\\javaStudy\\upload";
		
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName:" + orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName:" + exName);
		
		//저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName:" + saveName);
		
		//파일경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath:" + filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize:" + fileSize);
		
		////파일 서버에 복사//////////////////////////////////////
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//파일-->필요한정보추출-->DB에저장
		//		no:
		//		orgName:LeeHyoRi.jpg
		//		saveName:15953204064159993b09f-a8c8-4bb6-948b-c7e52be8a38a.jpg
		//		filePath:C:\\javaStudy\\upload\\15953204064159993b09f-a8c8-4bb6-948b-c7e52be8a38a.jpg
		//		fileSize:87271
		
		return saveName;
	}
	
}
