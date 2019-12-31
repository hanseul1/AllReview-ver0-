package com.hs.review.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.review.dto.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	SqlSession session;
	
	/** 회원가입 기능 */
	public void signUp(User user) {
		session.insert("sql.user.insert", user);
	}
	
	/** 아이디로 회원 찾기 기능 */
	public User searchUser(String id) {
		return session.selectOne("sql.user.searchById", id);
	}
	
	/** 로그인 기능 */
	public User login(User user) {
		return session.selectOne("sql.user.login", user);
	}
	
	/** 회원 정보 수정 기능 */
	public void update(User user) {
		session.update("sql.user.update", user);
	}
	
	/** 회원 정보 삭제 기능 
	 	return 삭제된 회원 수
	 */
	public int delete(User user) {
		return session.delete("sql.user.delete", user);
	}
}
