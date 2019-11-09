package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import database.Database;
import interfaces.OrderListenerAdm;
import interfaces.RegisterListener;
import models.OrderModelAdm;
import views.OrderViewAdm;
import views.RegisterView;

public class OrderControllerAdm implements OrderListenerAdm {
	
	private OrderViewAdm view;
	
	public OrderControllerAdm(OrderViewAdm view) {
		this.view = view;
	}
	
	@Override
	public void orderAdmPerformed(OrderModelAdm event) throws SQLException {
		System.out.println("Orders processed: ");
		

			
	}
}
