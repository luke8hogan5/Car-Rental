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
import views.MasterView;

public class LoginController implements LoginListener {
	private LoginView view;
	
	public LoginController(LoginView view) {
		this.view = view;
	}
	
	@Override
	public void loginPerformed(String name, String pass, MasterView master) throws SQLException {
		System.out.println("Login event received: " + name + "; " + pass);
		
		Connection conn = Database.getConnection();
		String stmt = "SELECT * FROM  account WHERE userName=? AND userPassword=?;";

		PreparedStatement ps = conn.prepareStatement(stmt);
		ps.setString(1,name);
		ps.setString(2,pass);
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

