package com.hs.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.review.dao.UserDao;
import com.hs.review.dto.User;
import com.hs.review.util.BcryptUtil;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	/** id로 회원 정보 찾기 */
	public User searchUser(String id){
		return userDao.searchUser(id);
	}
	
	/** 회원 가입 */
	public void signup(User user) {
		user.setPw(BcryptUtil.encrypt(user.getPw()));
		userDao.signUp(user);
	}
	
	/** 로그인 기능
	 * 	return 로그인한 유저 정보
	 */
	public User login(User user) {
		User result = userDao.login(user);
		if(result == null || 
				!BcryptUtil.matches(user.getPw(), result.getPw())) 
			return null;
		return result;
	}
	
	/** 회원 정보 수정 */
	public void updateUser(User user) {
		user.setPw(BcryptUtil.encrypt(user.getPw()));
		userDao.update(user);
	}
	
	/** 회원 탈퇴 
	 * 	return 탈퇴 성공 여부
	 */
	public boolean deleteUser(String id) {
		return (userDao.delete(id) != 0);
	}
}
