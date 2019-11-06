package controllers;

import interfaces.OrderListener;
import models.UserModel;
import views.OrderView;

public class OrderController implements OrderListener  {
private OrderView view;
	
	public OrderController(OrderView view) {
		this.view = view;
	}
	
	@Override
	public void orderPerformed(UserModel event) {
		//System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
		//add to database
	}

	@Override
	public void OrderPerformed(UserModel event) {
		//System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
		
	}

	
}

