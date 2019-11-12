package dao;

import java.util.List;

import models.OrderModel;

public interface OrderDao {

	OrderModel getOrder();

	List<OrderModel> getAllOrders();

	void insertOrder(OrderModel order);

	void deleteOrder(int orderId);

	void insertOrderAdm(OrderModel order);

	void updateOrderAdm(OrderModel order);

	int getOrderId();
	
	void updatePayment(int id);

}
