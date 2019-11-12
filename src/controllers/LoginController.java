package controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.AuthenticationDao;
import daoImpl.AuthenticationDaoImpl;
import database.Database;
import interfaces.LoginListener;
import models.UserModel;
import views.LoginView;
import views.MasterView;

public class LoginController implements LoginListener {
	private AuthenticationDaoImpl dao = new AuthenticationDaoImpl();
	private LoginView view;

	public LoginController(LoginView view) {
		this.view = view;
	}

	@Override
	public void loginPerformed(String name, String pass, MasterView master) throws SQLException {

		UserModel currentUser = dao.validateLogin(name, pass);
		master.setCurrentUser(currentUser);
	}
}

