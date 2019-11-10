package controllers;

import interfaces.OrderExtListener;
import models.VehicleModel;
import views.OrderView;
import views.OrderViewExt;

import java.sql.SQLException;

public class OrderExtController implements OrderExtListener {

    private OrderViewExt view;
    public OrderExtController(OrderViewExt view) {
        this.view = view;
    }

    @Override
    public void orderPerformed(VehicleModel event) throws SQLException {
        //view.pack();
    }
}
