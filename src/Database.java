

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static Database instance = new Database();
	
	private Connection con;
	
	public Database() {
		
	}
	
	public static Database getInstance() {
		return instance;
	}
	
	/*
	 * Add whatever methods you like to your singleton class.
	 */
	public boolean connect() throws Exception {
		boolean bool = true;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = String.format("jdbc:mysql://sql2.freemysqlhosting.net/", 3306);

		con = DriverManager.getConnection(url, "sql2310355", "gK5%qI5%");
		if(con == null){
			bool = false;
			
			return bool;
		}
		else {
			return bool;
		}
	}
	
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
		
		con = null;
	}
	
}
