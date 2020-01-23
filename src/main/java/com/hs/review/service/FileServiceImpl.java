package com.hs.review.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	
	/** 이미지 파일 저장 */
	public List<String> insertFiles(MultipartFile[] files) throws IllegalStateException, IOException {
		// 각 파일들의 새로 생성된 이름을 저장할 리스트
		List<String> fileNames = new ArrayList<>();
		for(MultipartFile file: files) {
			String rfileName = file.getOriginalFilename();
			
			// 중복된 이름의 파일이 서버에 이미 있을 경우를 대비해 
			// 파일 이름 앞에 현재 시간을 붙여 새로운 파일 이름을 생성한다.
			String sfileName = String.format("%d%s"
											, System.currentTimeMillis()
											, rfileName);
			File realFile = new File(new ClassPathResource("application.properties").getURI().getPath()
										+"/../static/static/img/"+ sfileName);
			file.transferTo(realFile);
			fileNames.add(sfileName);
		}
		return fileNames;
	}
}
