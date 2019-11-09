package interfaces;

import java.sql.SQLException;

import models.OrderModelAdm;

public interface OrderListenerAdm {

	public void orderAdmPerformed(OrderModelAdm event) throws SQLException;
}
