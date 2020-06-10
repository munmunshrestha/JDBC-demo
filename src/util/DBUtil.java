package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static final String URL = "jdbc:mysql://localhost:3306/lab1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String USER_NAME="root";
	public static final String PASSWORD="root";
	public static final String DRIVER_NAME ="com.mysql.cj.jdbc.Driver";

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	}

}
