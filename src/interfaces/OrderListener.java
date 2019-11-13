package interfaces;
import models.OrderModel;
import models.UserModel;
import models.VehicleModel;
import views.MasterView;

import java.sql.SQLException;

public interface OrderListener {
    OrderModel getNewOrder();

    void orderSubmited(int userId, int vehicleId, int rentDuration, boolean paymentCleared, MasterView master) throws SQLException;

    double totalDiscounts(int loyaltyRating);
}

