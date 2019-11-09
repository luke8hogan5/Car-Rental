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
		//view.pack();
	}

}

