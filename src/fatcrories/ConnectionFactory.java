package fatcrories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private String url = "jdbc:mysql://localhost:3306/aula04java?useTimezone=true&serverTimezone=UTC&useSSL=false";
	private String user = "root";
	private String password = "scm300501";

	public Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, user, password);
	}

}
