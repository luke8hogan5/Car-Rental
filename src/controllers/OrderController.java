package controllers;

import database.Database;
import interfaces.OrderListener;
import models.OrderModel;
import models.UserModel;
import models.VehicleModel;
import views.OrderView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		System.out.println("Order Recieved: " + event.getVehicleMake() + "; " + event.getVehicleModel());
		String vMake = event.getVehicleMake();
		String vModel = event.getVehicleMake();
		int vYear = event.getVehicleYear();

		Connection conn = Database.getConnection();
		String query = "SELECT vehicleModel,vehicleMake,vehicleYear FROM  vehicle;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			String make = rs.getString("vehicleMake");
			String model = rs.getString("vehicleModel");
			int year  = rs.getInt("vehicleYear");

			if(vMake.equals(make) && vModel.equals(model) && (vYear == year)) {
				System.out.println("Order Confirmed");
			}
		}
	}
}

