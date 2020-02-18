package com.hs.review.service;

import java.util.Map;

public interface JwtService {

	long JWT_TOKEN_VALIDITY = 60 * 60 * 5;

	/**jwt 토큰 생성*/
	<T> String create(String key, T data, String subject);

	/** jwt private hash code 생성 */
	byte[] generateKey();

	/**
	 * 유효한 토큰인지 검증  발생하는 에러 5가지
	 * 
	 * 1) ExpiredJwtException : JWT를 생성할 때 지정한 유효기간 초과할 때.
	 * 2) UnsupportedJwtException : 예상하는 형식과 일치하지 않는 특정 형식이나 구성의 JWT일 때
	 * 3) MalformedJwtException : JWT가 올바르게 구성되지 않았을 때
	 * 4) SignatureException : JWT의 기존 서명을 확인하지 못했을 때
	 * 5) IllegalArgumentException
	 */
	boolean isUsable(String jwt);

	/** Jwt token 에서 사용자의 data값 받아오기*/
	Map<String, Object> get(String key);

}