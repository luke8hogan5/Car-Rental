package controllers;

import database.Database;
import interfaces.OrderListener;
import models.OrderModel;
import models.UserModel;
import models.VehicleModel;
import views.OrderView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderController implements OrderListener  {
private OrderView view;
	
	public OrderController(OrderView view) {
		this.view = view;
	}
	
	@Override
	public void orderPerformed(VehicleModel event) throws SQLException {

		System.out.println("Order Recieved: " + event.getName() + "; " + event.getPassword());

		String username = event.getMake();
		String password = event.getPassword();

		Connection conn = Database.getConnection();
		String query = "SELECT userName, userPassword FROM  account;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			String user = rs.getString("userName");
			String pass = rs.getString("userPassword");

			if(username.equals(user) && password.equals(password)) {
				System.out.println("Login performed");
			}
		}
	}

	@Override
	public void OrderPerformed(UserModel event) {
		//System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
		
	}

	
}

