package interfaces;

import java.sql.SQLException;

import models.OrderModel;
import models.PaymentModel;

public interface PaymentListener {

	public void paymentPerformed(PaymentModel event,OrderModel newOrder) throws SQLException;

}
