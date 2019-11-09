import java.sql.SQLException;

import javax.swing.SwingUtilities;

import controllers.LoginController;
import controllers.RegisterController;
import database.Database;
import views.LoginView;
import views.MasterView;
import views.RegisterView;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Database db = null;
				try {
					db = Database.getInstance();
				} catch (SQLException e1) {
					System.out.print("Database not initialised");
					e1.printStackTrace();
				}
				if(db != null) {
					//initLogin();
					initRegister();
				}
				
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void initLogin() {	
		LoginView logView = new LoginView();
		LoginController logCtrl = new LoginController(logView);
			
		logView.setLoginListener(logCtrl);
	}
		
		public static void initRegister() {
			RegisterView regView = new RegisterView();
			RegisterController regCtrl = new RegisterController(regView);
			
			regView.setRegisterListener(regCtrl);
		}
}


