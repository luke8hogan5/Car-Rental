package controllers;

import daoImpl.OrderDaoImpl;
import interfaces.OrderListener;
import models.OrderModel;

public class OrderController implements OrderListener  {

	private OrderDaoImpl dao = new OrderDaoImpl();
	private OrderModel newOrder;
	
	public OrderController() {
	}

   /**
  	* @return current order being processed.
    */
	@Override
	public OrderModel getNewOrder(){
		return newOrder;
	}


   /**
	* Calls DAO to add order details to DB
	*
	* @param userId			Current user's ID#
	* @param vehicleId		Selected vehicle's ID#
	* @param rentDuration	Specified duration of rent
	* @param paymentCleared	Whether user has paid by cash
	*/
	@Override
	public void orderSubmited(int userId, int vehicleId, int rentDuration, boolean paymentCleared) {

		newOrder = new OrderModel(userId, vehicleId,rentDuration, paymentCleared);

		dao.insertOrder(newOrder);

		newOrder.setOrderId(dao.getOrderId());

	}

   /**
	* Decides discount on order based on their loyalty score
	* @param loyaltyRating Current users total loyalty points.
	* @return discount rate on order
	*/
	@Override
	public double totalDiscounts(int loyaltyRating) {
		double rate = 0.00;

		if (loyaltyRating > 600) {
			rate = .10;
		} else if (loyaltyRating > 400) {
			rate = .08;
		} else if (loyaltyRating > 200) {
			rate = .06;
		} else if (loyaltyRating > 100) {
			rate = .04;
		} else if (loyaltyRating > 50) {
			rate = .02;
		}

		return rate;
	}

}

