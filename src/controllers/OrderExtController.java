package controllers;

import daoImpl.OrderDaoImpl;
import interfaces.OrderExtListener;
import models.OrderModel;
import views.MasterView;
import views.OrderViewExt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import static java.lang.String.valueOf;

public class OrderExtController implements OrderExtListener {

    private OrderDaoImpl dao = new OrderDaoImpl();
    private OrderModel newOrder;
    private OrderViewExt view;
    public OrderExtController(OrderViewExt view) {
        this.view = view;
    }

    @Override
    public void orderSubmited(int userId,int vehicleId,int rentDuration,boolean paymentCleared, MasterView master) throws SQLException {

        newOrder = new OrderModel(userId, vehicleId,rentDuration, paymentCleared);

        dao.insertOrder(newOrder);

        newOrder.setOrderId(dao.getOrderId());

    }
    public OrderModel getNewOrder(){
        return newOrder;
    }

    public String calNetCost(double vCostPerDay, double totalDiscountsPerDay){
        String netCost = "";
        double netCostPerDay = 0.00;
        double userCost = vCostPerDay - totalDiscountsPerDay;

        BigDecimal vCost = BigDecimal.valueOf(vCostPerDay);
        vCost = vCost.setScale(2, RoundingMode.HALF_UP);

        BigDecimal bd = BigDecimal.valueOf(userCost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        netCostPerDay = bd.doubleValue();

        int totalCostPerDay = (int) Math.round(vCostPerDay);

        netCost += valueOf(vCost.doubleValue()) + " - " + valueOf(totalDiscountsPerDay) + " = " + valueOf(netCostPerDay) + "";
        return netCost;
    }

    public double totalDiscounts ( int loyaltyRating, double vCost){
        double totalDiscount = 0.00, rate = 0.00;

        if(loyaltyRating > 600){
            rate = vCost * 0.10;
        }else if(loyaltyRating > 400) {
            rate = vCost * 0.08;
        }else if(loyaltyRating > 200) {
            rate = vCost * 0.06;
        }else if(loyaltyRating > 100) {
            rate = vCost * 0.04;
        }else if(loyaltyRating > 50) {
            rate = vCost * 0.02;
        }else {
            rate = 0;
        }

        BigDecimal bd = BigDecimal.valueOf(rate);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        totalDiscount += bd.doubleValue();
        return totalDiscount;
    }
}
