package controllers;

import java.sql.*;
import java.util.Vector;

import daoImpl.OrderDaoImpl;
import interfaces.OrderListenerAdm;

public class OrderControllerAdm implements OrderListenerAdm {

	private OrderDaoImpl dao = new OrderDaoImpl();
	
	public OrderControllerAdm() {
	}


   /**
	* Gets data on orders from database by calling on the DAO
	* @return Data split into multidimen array
	*/
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
