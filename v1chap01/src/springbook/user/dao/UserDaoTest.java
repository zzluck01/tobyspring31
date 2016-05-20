package springbook.user.dao;

import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		UserDao dao = new NUserDao();
		
		User user = new User();
		user.setId("zzluck01");
		user.setName("luckyGuy");
		user.setPassword("pw");
		
		dao.add(user);			// 사용자 등록.
		
		System.out.println(user.getId() + " 등록 성공.");
		
		User user2 = dao.get(user.getId());	//	등록된 사용자 조회.
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + " 조회 성공.");
		
		dao.delete(user2.getId());
		System.out.println(user2.getId() + " 삭제 성공.");
	}
}
