package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;
import interfaces.CatalogListener;
import models.CatalogModel;
import views.CatalogView1;



public class CatalogController implements CatalogListener {

	private CatalogView1 Cview;
	
		public CatalogController(CatalogView1 Cview) {
			this.Cview = Cview;
		}
	
		@Override
		public void CatalogPerformed(CatalogModel event) throws SQLException{
			//System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
			
		}
	

}

/*
package controllers;

import interfaces.OrderListener;
import models.UserModel;
import views.OrderView;

public class OrderController implements OrderListener  {
private OrderView view;
	
	public OrderController(OrderView view) {
		this.view = view;
	}
*/