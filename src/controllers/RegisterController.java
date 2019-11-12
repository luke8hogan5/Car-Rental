package controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daoImpl.AuthenticationDaoImpl;
import database.Database;
import interfaces.RegisterListener;
import models.UserModel;
import views.MasterView;
import views.RegisterView;

public class RegisterController implements RegisterListener {
	private RegisterView view;

	private AuthenticationDaoImpl dao = new AuthenticationDaoImpl();

	public RegisterController(RegisterView view) {
		this.view = view;
	}

	@Override
	public void registerPerformed(String name, String pass, String _email, MasterView master) throws SQLException {
		System.out.println("Register event received: " + name + "; " + pass);

		dao.registerUser(name,pass,_email);
		UserModel currentUser = dao.loginAfterRegister();
	   	master.setCurrentUser(currentUser);
	}
}
