package com.hs.review.util;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;


public class JwtUtil {
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
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SIGN_KEY))
				                  .withIssuer(issuer)
				                  .build();
		verifier.verify(requestToken);
	}
}
