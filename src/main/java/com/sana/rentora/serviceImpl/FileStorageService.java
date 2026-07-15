package com.sana.rentora.serviceImpl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	
	private final String BASE_DIR = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;
	
	public String saveFile(MultipartFile file, String folder) {
		
		try {
			String dirPath = BASE_DIR + folder;
			File dir = new File(dirPath);
			
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String filePath = dirPath + "/" + file.getOriginalFilename();
			
			file.transferTo(new File(filePath));
			
			return filePath;
		} catch(Exception e) {
			throw new RuntimeException("File upload failed" + e);
		}
	}
}
