package com.hs.review.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hs.review.dao.UserDao;
import com.hs.review.util.JwtUtil;

public class JwtAuthInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDao userDao;
	
	private String HEADER_TOKEN_KEY = "Authorization";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestToken = request.getHeader(HEADER_TOKEN_KEY);
		
		// 토큰 검증 실패하면 Exception 발생 시킴
		jwtUtil.verifyToken(requestToken);
		return true;
	}
}
