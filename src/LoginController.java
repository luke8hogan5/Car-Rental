public class LoginController implements LoginListener {
	private LoginView view;
	
	public LoginController(LoginView view) {
		this.view = view;
	}

	@Override
	public void loginPerformed(LoginModel event) {
		System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
		//add to database
	}
	
	
}
