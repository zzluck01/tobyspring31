package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.domain.User;

public class UserDaoConnectionCountingTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		AnnotationConfigApplicationContext context= 
				new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("zzluck01");
		user.setName("lucky brain");
		user.setPassword("password!");
		dao.add(user);
		
		System.out.println(user.getId() + " 등록 성공.");
	
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + " 조회 성공.");
		
		dao.delete(user2.getId());
		System.out.println(user2.getId() + " 삭제 성공.");
		
		CountingConnectionMaker ccm = context.getBean("connectionMaker",CountingConnectionMaker.class);
		System.out.println("Connection counter : " + ccm.getCounter());
	}
}
