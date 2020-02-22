package com.hs.review.util;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptUtil {
	
	/** 입력받은 비밀번호 암호화하여 리턴 */
	public static String encrypt(String pw) {
		return BCrypt.hashpw(pw, BCrypt.gensalt());
	}
	
	/** 입력받은 비밀번호와 DB에 저장된 암호화 비밀번호의 일치여부 리턴 */
	public static boolean matches(String pw, String hashed) {
		return BCrypt.checkpw(pw, hashed);
	}
}
