package com.hs.review.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hs.review.util.RestUtil;

@Controller
@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler
	public ResponseEntity<Map<String,Object>> handle(Exception e){
		return RestUtil.handleFail(e.getMessage(), HttpStatus.ACCEPTED);
	}
}
