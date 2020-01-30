package com.hs.review.util;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;


//public class JwtUtilImpl implements JwtUtil {
	public class JwtUtilImpl  {
	private final static String SIGN_KEY = "Allreview_sign_key";
	private static Date expiredTime = new Date(System.currentTimeMillis() + 1000000000 * 10);
	private static String issuer = "Allreview";
	
	/** 사용자 토큰 발급 */
	public static String CreateToken() {
		return JWT.create()
				  .withIssuer(issuer)
				  .withExpiresAt(expiredTime)
				  .sign(Algorithm.HMAC256(SIGN_KEY));
	}
	
	/** 사용자 요청 토큰 검증 */
	public static void verifyToken(String requestToken) {
		System.out.println("00000000000000000000000");
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SIGN_KEY))
				                  .withIssuer(issuer)
				                  .build();
		System.out.println("111111111111111111111");
		System.out.println(requestToken);
		verifier.verify(requestToken);
	}
}
