package controllers;

import interfaces.OrderExtListener;
import models.VehicleModel;
import views.OrderView;

import java.sql.SQLException;

public class OrderExtController implements OrderExtListener {

    private OrderView view;
    public OrderExtController(OrderView view) {
        this.view = view;
    }

    @Override
    public void orderPerformed(VehicleModel event) throws SQLException {
        //view.pack();
    }
}
