package dao;

import java.util.List;

import models.OrderModel;

public interface OrderDao {

	OrderModel getOrder();

	List<OrderModel> getAllOrders();

	void insertOrder(OrderModel order);

	void updateOrder(OrderModel order);

	void deleteOrder(int orderId);

}
