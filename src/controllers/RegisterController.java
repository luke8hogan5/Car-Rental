package controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;
import interfaces.RegisterListener;
import models.UserModel;
import views.RegisterView;

public class RegisterController implements RegisterListener {
	private RegisterView view;
	
	public RegisterController(RegisterView view) {
		this.view = view;
	}

	@Override
	public void registerPerformed(UserModel event) throws SQLException {
		System.out.println("Register event received: " + event.getName() + "; " + event.getPassword());
		
		String username = event.getName();
		String password = event.getPassword();
		String email = event.getEmail();
		
		Connection conn = Database.getConnection();
		
		String query = "INSERT INTO `account`(`userName`,`userPassword`, email) VALUES (?,?,?);";
		
        PreparedStatement ps = conn.prepareStatement(query); 
		ps.setString(1, username);
	    ps.setString(2, password);
	    ps.setString(3, email);
	    ps.executeUpdate(); 
	}
	
	
}    