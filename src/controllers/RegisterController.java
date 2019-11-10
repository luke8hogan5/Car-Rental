package controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;
import interfaces.RegisterListener;
import models.UserModel;
import views.MasterView;
import views.RegisterView;

public class RegisterController implements RegisterListener {
	private RegisterView view;
	
	public RegisterController(RegisterView view) {
		this.view = view;
	}

	@Override
	public void registerPerformed(String name, String pass, String _email, MasterView master) throws SQLException {
		System.out.println("Register event received: " + name + "; " + pass);
		
		String username = name;
		String password = pass;
		String email = _email;
		
		Connection conn = Database.getConnection();
		
		String query = "INSERT INTO `account`(`userName`,`userPassword`, email) VALUES (?,?,?);";
		
        PreparedStatement ps = conn.prepareStatement(query); 
		ps.setString(1, username);
	    ps.setString(2, password);
	    ps.setString(3, email);
	    ps.executeUpdate();

	    String stmt = "SELECT * FROM account WHERE user_id =(" +
							"SELECT MAX(user_id) FROM account" +
						");";
	    ps = conn.prepareStatement(stmt);
		ResultSet currentUser = ps.executeQuery();
		while(currentUser.next())
		    master.setCurrentUser(new UserModel(currentUser.getInt(1),
												currentUser.getInt(2),
												currentUser.getString(3),
												currentUser.getString(5),
												currentUser.getString(6),
												currentUser.getInt(7),
												currentUser.getDouble(8)));
	}
	
	
}    