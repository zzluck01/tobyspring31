package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		ApplicationContext context= 	//빈팩토리의 확장. 
				new AnnotationConfigApplicationContext(DaoFactory.class);	//configuration 애노테이션 사용한다는 설정.
				//new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao",UserDao.class);	//메소드 명 = 빈 명.
				//new UserDao(connectionMaker);
		//ConnectionMaker connectionMaker = new DConnectionMaker();
		
		
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
