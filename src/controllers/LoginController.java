package controllers;
import interfaces.LoginListener;
import models.UserModel;
import views.LoginView;

public class LoginController implements LoginListener {
	private LoginView view;
	
	public LoginController(LoginView view) {
		this.view = view;
	}

	@Override
	public void loginPerformed(UserModel event) {
		System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
		//add to database
	}
	
	
}
