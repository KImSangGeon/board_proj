package board_proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {	
	
public static Connection getConnection() {
	
//	웹에선 테스트 못한다/		
		Connection con = null;		
			try {
				String url = "jdbc:mysql://localhost:3306/web_gradle_erp?useSSL=false";
				String id = "user_gradle_erp";				
				String passwd = "rootroot";
				
				con = DriverManager.getConnection(url, id, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}	

		return con;
	}
}
