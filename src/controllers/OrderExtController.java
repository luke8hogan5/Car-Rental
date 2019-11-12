package controllers;

import database.Database;
import interfaces.OrderExtListener;
import models.OrderModel;
import models.UserModel;
import models.VehicleModel;
import views.MasterView;
import views.OrderView;
import views.OrderViewExt;
import views.PaymentView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderExtController implements OrderExtListener {

    private OrderViewExt view;
    public OrderExtController(OrderViewExt view) {
        this.view = view;
    }

    @Override
    public void orderSubmited(int userId,int vehicleId,int rentDuration,boolean paymentClear, MasterView master) throws SQLException {

        OrderModel newOrder = new OrderModel(userId, vehicleId, false);
        String updateTable = "INSERT INTO  `orderTable`(`vehicle_id`,`user_id`,`rentDuration`,`paymentCleared`) VALUES (?,?,?,?);";
        Connection conn = Database.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(updateTable);
            ps.setInt(1, vehicleId);
            ps.setInt(2, userId);
            ps.setInt(3,rentDuration);
            ps.setBoolean(4, newOrder.getPaymentCleared());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
