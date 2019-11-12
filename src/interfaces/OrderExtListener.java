package interfaces;

import models.VehicleModel;
import views.MasterView;

import java.sql.SQLException;

public interface OrderExtListener {
    //public void orderPerformed(VehicleModel event) throws SQLException;
    public void orderSubmited(int userId,int vehicleId,int rentDuration,boolean paymentClear, MasterView master) throws SQLException;

}

