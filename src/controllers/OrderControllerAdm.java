package controllers;

import java.sql.*;
import java.util.Vector;

import database.Database;
import interfaces.OrderListenerAdm;
import views.OrderViewAdm;

public class OrderControllerAdm implements OrderListenerAdm {
	
	private OrderViewAdm view;
	
	public OrderControllerAdm(OrderViewAdm view) {
		this.view = view;
	}
	
	@Override
	public Vector<Vector<Object>> orderAdmPerformed() throws SQLException {
		System.out.println("Orders processed: ");

		Connection conn = Database.getConnection();
		String sql = "SELECT dateCreate, rentDuration, paymentCleared FROM orderTable where vehicleReturned = 0;";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		Vector<Vector<Object>> data = new Vector<>();

		while(rs.next()){
			Vector<Object> dataLine = new Vector<>();
			dataLine.add(rs.getString("dateCreate"));
			dataLine.add(rs.getInt("rentDuration"));
			dataLine.add(rs.getString("paymentCleared").equals("0") ? "No" : "Yes");

			data.add(dataLine);
		}

		return data;
	}
}
