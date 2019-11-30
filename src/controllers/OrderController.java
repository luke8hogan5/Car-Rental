package controllers;

import daoImpl.OrderDaoImpl;
import interfaces.OrderListener;
import models.OrderModel;
import models.VehicleModel;
import views.MasterView;
import views.OrderView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import static java.lang.String.valueOf;

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

//	@Override
//	public void orderPerformed(VehicleModel event) throws SQLException {
//		//view.pack();
//	}

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

//	@Override
//	public String calNetCost(double vCostPerDay, double totalDiscountsPerDay){
//		String netCost = "";
//		double netCostPerDay = 0.00;
//		double userCost = vCostPerDay - totalDiscountsPerDay;
//
//		BigDecimal vCost = BigDecimal.valueOf(vCostPerDay);
//		vCost = vCost.setScale(2, RoundingMode.HALF_UP);
//
//		BigDecimal bd = BigDecimal.valueOf(userCost);
//		bd = bd.setScale(2, RoundingMode.HALF_UP);
//		netCostPerDay = bd.doubleValue();
//
//		int totalCostPerDay = (int) Math.round(vCostPerDay);
//
//		netCost += valueOf(vCost.doubleValue()) + " - " + valueOf(totalDiscountsPerDay) + " = " + valueOf(netCostPerDay) + "";
//		return netCost;
//	}

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

