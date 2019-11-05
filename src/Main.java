import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Database db = new Database();
				try {
					if(db.connect() == true)
//					MainWindow main = new MainWindow();
//			        main.setVisible(true);
				    regGUI();
//					logGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
		public static void regGUI() {
			RegisterView view = new RegisterView();
			RegisterController controller = new RegisterController(view);
			
			view.setRegisterListener(controller);
		}
		public static void logGUI() {
			LoginView view = new LoginView();
			LoginController controller = new LoginController(view);
			
			view.setLoginListener(controller);
		}
}


