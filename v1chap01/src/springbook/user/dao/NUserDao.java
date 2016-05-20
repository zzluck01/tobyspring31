package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {

	//@Override //  추상 클래스였을 때 사용된 것.
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		//N사 DB Connection 생성코드
		Class.forName("com.mysql.jdbc.Driver");
		Connection c =
				DriverManager.getConnection(
						"jdbc:mysql://localhost/springbook","spring","book");
		return c;
	}

}