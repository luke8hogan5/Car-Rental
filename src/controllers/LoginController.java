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

	public LoginController() {
	}

   /**
	* Calls DAO to try logging in user
	* @param name User's name
	* @param pass User's password
	* @param master Current top-level view ancestor to allow the current user info
	*/
	@Override
	public void loginPerformed(String name, String pass, MasterView master) {

		UserModel currentUser = dao.validateLogin(name, pass);
		master.setCurrentUser(currentUser);
	}
}

