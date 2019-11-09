package controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;
import interfaces.LoginListener;
import models.UserModel;
import views.LoginView;

public class LoginController implements LoginListener {
	private LoginView view;
	
	public LoginController(LoginView view) {
		this.view = view;
	}
	
	@Override
	public void loginPerformed(UserModel event) throws SQLException {
		System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
<<<<<<< HEAD
		
=======

>>>>>>> 246bbd16297e5ddccc1701a7c289c4e98ae064e2
		String username = event.getName();
		String password = event.getPassword();
		
		Connection conn = Database.getConnection();
		String query = "SELECT userName, userPassword FROM  account;";
		Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    
	    while(rs.next()) {
	    	String user = rs.getString("userName");
	    	String pass = rs.getString("userPassword");
	        
			if(username.equals(user) && password.equals(password)) {
		    	System.out.println("Login performed");
			}
	    }
	}
}
