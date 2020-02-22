package com.hs.review.service;

import com.hs.review.dto.User;

public interface UserService {

	/** id로 회원 정보 찾기 */
	User searchUser(String id);

	/** 회원 가입 */
	void signup(User user);

	/** 로그인 기능
	 * 	return 로그인한 유저 정보
	 */
	User login(User user);

	/** 회원 정보 수정 */
	void update(User user);

	/** 회원 탈퇴 
	 * 	return 탈퇴 성공 여부
	 */
	boolean delete(String id);

}