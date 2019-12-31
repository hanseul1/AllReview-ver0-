package com.hs.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.review.dao.UserDao;
import com.hs.review.dto.User;

@Service
public class UserServiceImpl {
	@Autowired
	private UserDao userDao;
	
	/** 아이디 중복 체크
	  * true면 아이디 사용 가능, false면 아이디 사용 불가
	  */
	public boolean idCheck(String id) {
		return (userDao.searchUser(id) == null);
	}
	
	/** 회원 가입 */
	public void signup(User user) {
		userDao.signUp(user);
	}
	
	/** 로그인 기능
	 * 	return 로그인 성공 여부
	 */
	public boolean login(User user) {
		return (userDao.login(user) != null);
	}
	
	/** 회원 정보 수정 */
	public void updateUser(User user) {
		userDao.update(user);
	}
	
	/** 회원 탈퇴 
	 * 	return 탈퇴 성공 여부
	 */
	public boolean deleteUser(User user) {
		return (userDao.delete(user) != 0);
	}
}
