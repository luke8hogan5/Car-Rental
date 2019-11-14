package controllers;

import java.sql.*;
import java.util.Vector;

import daoImpl.OrderDaoImpl;
import database.Database;
import interfaces.OrderListenerAdm;
import views.OrderViewAdm;

public class OrderControllerAdm implements OrderListenerAdm {
	
	private OrderViewAdm view;
	private OrderDaoImpl dao = new OrderDaoImpl();
	
	public OrderControllerAdm(OrderViewAdm view) {
		this.view = view;
	}
	
	@Override
	public Vector<Vector<Object>> orderAdmPerformed() throws SQLException {
		System.out.println("Orders processed: ");

		ResultSet rs = dao.getAllOrdersAdm();
		

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
