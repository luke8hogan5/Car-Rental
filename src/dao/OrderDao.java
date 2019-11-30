package dao;

import java.sql.ResultSet;

import models.OrderModel;

public interface OrderDao {

	ResultSet getAllOrdersAdm();

	void insertOrder(OrderModel order);

	int getOrderId();
	
	void updatePayment(int id);

}
