package highradius;

import java.sql.*;

public class DBConnection {
	public static Connection createConnection() {
		Connection con = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url      = System.getenv().getOrDefault("DB_URL",  "jdbc:mysql://localhost:3306/grey_goose");
		String user     = System.getenv().getOrDefault("DB_USER", "root");
		String password = System.getenv("DB_PASSWORD"); // must be set; no default
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Database Connection established");
		}catch (SQLException | ClassNotFoundException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		return con;
	}
}
