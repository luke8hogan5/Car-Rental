package interfaces;

import java.sql.SQLException;
import java.util.Vector;

public interface VehicleListenerAdm {

	Vector<Vector<Object>> getVehicles() throws SQLException;

	void updatePerformed(int id, String make, String model, int year, double price, int available, String type) throws SQLException;

	void deletePerformed(int id) throws SQLException;

	void addPerformed(String make, String model, int year, double price, String type) throws SQLException;
}
