package com.hs.review.dao;

import com.hs.review.dto.User;

public interface UserDao {

	/** 회원가입 기능 */
	void signUp(User user);

	/** 아이디로 회원 찾기 기능 */
	User searchUser(String id);

	/** 로그인 기능 */
	User login(User user);
	
	/** 회원 정보 수정 기능 */
	void update(User user);

	/** 회원 정보 삭제 기능 
	 	return 삭제된 회원 수
	 */
	int delete(String id);

}