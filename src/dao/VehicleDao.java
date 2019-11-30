package dao;

import java.sql.ResultSet;

public interface VehicleDao {

	ResultSet getCatalog();

	ResultSet getSearchResults(String keyword);

	ResultSet getAllVehiclesAdm();

	void updateVehicleAdm(int id, String make, String model, int year, double price, int available, String type);

	void deleteVehicleAdm(int vehicleId);

	void insertVehicleAdm(String make, String model, int year, double price, String type);
	

}
