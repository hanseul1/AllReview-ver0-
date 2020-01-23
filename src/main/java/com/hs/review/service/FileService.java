package com.hs.review.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	/** 이미지 파일 저장 */
	String[] insertFiles(MultipartFile[] files) throws IllegalStateException, IOException;

}