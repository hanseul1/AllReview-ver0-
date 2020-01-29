package com.hs.review.util;

public interface JwtUtil {

	/** 사용자 토큰 발급 */
	String CreateToken();

	/** 사용자 요청 토큰 검증 */
	void verifyToken(String requestToken);

}