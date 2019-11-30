package interfaces;
import models.OrderModel;

import java.sql.SQLException;

public interface OrderListener {
    OrderModel getNewOrder();

    void orderSubmited(int userId, int vehicleId, int rentDuration, boolean paymentCleared) throws SQLException;

    double totalDiscounts(int loyaltyRating);
}

