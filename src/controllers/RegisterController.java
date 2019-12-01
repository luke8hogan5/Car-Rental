package controllers;

import daoImpl.AuthenticationDaoImpl;
import interfaces.RegisterListener;
import models.UserModel;
import views.MasterView;

import java.sql.SQLException;

public class RegisterController implements RegisterListener {

	private AuthenticationDaoImpl dao = new AuthenticationDaoImpl();

	public RegisterController() {
	}

   /**
	* Calls DAO to register user using details they have provided.
	* @param name User's name
	* @param pass User's password
	* @param _email User's email
	* @param master Current top-level view ancestor to allow the current user info
	*/
	@Override
	public void registerPerformed(String name, String pass, String _email, MasterView master) {
		assert(name != null && pass != null && _email != null && master != null);
		System.out.println("Register event received: " + name + "; " + pass);

		dao.registerUser(name,pass,_email);
		UserModel currentUser = dao.loginAfterRegister();
	   	master.setCurrentUser(currentUser);
	   	assert(master.getCurrentUser() != null);
	}
}
