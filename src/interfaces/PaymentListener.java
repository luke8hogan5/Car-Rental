package interfaces;

import java.sql.SQLException;

import models.PaymentModel;

public interface PaymentListener {

	public void paymentPerformed(PaymentModel event) throws SQLException;

}
