package dao;

import java.util.List;

import models.OrderModel;

public interface OrderDao {

	OrderModel getOrder();

	List<OrderModel> getAllOrders();

	void insertVehicle(OrderModel order);

	void updateVehicle(OrderModel order);

	void deleteVehicle(int orderId);

}
