package interfaces;
import models.UserModel;
import models.VehicleModel;

import java.sql.SQLException;

public interface OrderListener {
	public void orderPerformed(VehicleModel event) throws SQLException;
}

