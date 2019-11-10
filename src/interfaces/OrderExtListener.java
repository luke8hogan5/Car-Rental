package interfaces;

import models.VehicleModel;

import java.sql.SQLException;

public interface OrderExtListener {
    public void orderPerformed(VehicleModel event) throws SQLException;
}

