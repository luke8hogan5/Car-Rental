import java.sql.SQLException;

import javax.swing.SwingUtilities;

import controllers.LoginController;
import controllers.OrderControllerAdm;
import controllers.PaymentController;
import controllers.RegisterController;
import controllers.UsersControllerAdm;
import controllers.VehicleControllerAdm;
import database.Database;
import views.UsersViewAdm;
import views.VehicleViewAdm;
import views.LoginView;
import views.MasterView;
import views.OrderViewAdm;
import views.PaymentView;
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
<<<<<<< HEAD
					try {
						//initUsersAdm();
						//initVehicleViewAdm();
						initUsersAdm();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
=======
					//initLogin();
					initRegister();
				}
				
				try {

				} catch (Exception e) {
					e.printStackTrace();
>>>>>>> 246bbd16297e5ddccc1701a7c289c4e98ae064e2
				}
			}
		});
	}
	public static void initLogin() {	
		LoginView logView = new LoginView();
		LoginController logCtrl = new LoginController(logView);
			
		logView.setLoginListener(logCtrl);
	}
<<<<<<< HEAD
	public static void initOrderAdm() throws SQLException {	
		OrderViewAdm orderViewAdm = new OrderViewAdm();
		OrderControllerAdm orderCtrlAdm = new OrderControllerAdm(orderViewAdm);
			
		orderViewAdm.setOrderAdmListener(orderCtrlAdm);
	}
	public static void initPaymentView() {	
		PaymentView payView = new PaymentView();
		PaymentController payCtrl = new PaymentController(payView);
			
		payView.setPaymentListener(payCtrl);
	}
	public static void initUsersAdm() throws SQLException {	
		UsersViewAdm view = new UsersViewAdm();
		
		UsersControllerAdm usersCtrl = new UsersControllerAdm(view);
		
		view.setUsersAdmListener(usersCtrl);
	}
	
	public static void initRegister() {
			RegisterView regView = new RegisterView();
			RegisterController regCtrl = new RegisterController(regView);
			
=======
		
		public static void initRegister() {
			RegisterView regView = new RegisterView();
			RegisterController regCtrl = new RegisterController(regView);
			
>>>>>>> 246bbd16297e5ddccc1701a7c289c4e98ae064e2
			regView.setRegisterListener(regCtrl);
		}
	public static void initVehicleViewAdm() throws SQLException {
		VehicleViewAdm vehicleView = new VehicleViewAdm();
		VehicleControllerAdm vehicleCtrl = new VehicleControllerAdm(vehicleView);
		
		vehicleView.setVehicleListener(vehicleCtrl);
	}
}


