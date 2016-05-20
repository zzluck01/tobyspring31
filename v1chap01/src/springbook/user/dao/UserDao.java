package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.domain.User;

/*
 * 작성자 : zzluck01
 * 작성일 : 2016년 5월 20일
 * 파일명 : UserDao.java
 * 파일 내용 :
 * 		- 사용자 정보를 DB에 넣고 관리하는 Dao클래스.
 * 		- add()클래스 : 사용자 정보 생성.
 * 		- get()클래스 : 사용자 정보 조회.
 * 		- JDBC를 이용한 작업 순서
 * 			!. DB연결 위한 Connection 생성
 * 			!. SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
 * 			!. Statement 실행
 * 			!. 조회의 경우 SQL 쿼리의 실행 결과를 ResultSet으로 받아서 정보를 저장할 오브젝트(User)에 옮김.
 * 			!. 작업 중 생성된 Connection, Statement, ResultSet은 작업 후 반드시 닫아준다.
 * 			!. JDBC가 만들어 내는 예외를 잡아 직접 처리하거나, throws를 선언 해 예외 발생 시 메소드 밖으로 던지게 함.
 */
public class UserDao {
	private ConnectionMaker connectionMaker;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker){
		this.connectionMaker = connectionMaker;
	}
	public UserDao(){
		//DaoFactory를 이용하는 생성자.
		/*DaoFactory daoFactory = new DaoFactory();
		this.connectionMaker = daoFactory.connectionMaker();*/
		
		//의존관계 검색을 이용하는 UserDao생성자
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DaoFactory.class);
		this.connectionMaker = context.getBean("connectionMaker",ConnectionMaker.class);
	}
	
	public UserDao(ConnectionMaker connectionMaker){
		this.connectionMaker = connectionMaker;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		// 사용자 정보를 등록한다.
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
					"insert into users(id, name, password) "
				+ 	"values	(?,?,?) ");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();	// INSERT, UPDATE, DELETE 일경우 실행된 row 갯수(int)값을 가져옴.
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		//사용자의 정보를 가져온다.
		Connection c =this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
					"select id, name, password "
				+ 	"from users "
				+ 	"where id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();	// 쿼리를 실행하고 ResultSet 값을 가져옴.
		rs.next();							// 현재 줄이 실행 가능한지?
		
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		return user;
	}
	
	public void delete(String id) throws ClassNotFoundException, SQLException{
		// 사용자 정보를 등록한다.
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
					"delete from users "
				+ 	"where id = ? ");
		ps.setString(1, id);
		
		ps.executeUpdate();	// INSERT, UPDATE, DELETE 일경우 실행된 row 갯수(int)값을 가져옴.
		
		ps.close();
		c.close();
	}
	
	/*public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c =
				DriverManager.getConnection(
						"jdbc:mysql://localhost/springbook","spring","book");
		return c;
	}*/
}
