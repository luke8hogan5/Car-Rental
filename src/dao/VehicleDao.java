package dao;

import java.sql.ResultSet;
import java.util.List;

import models.VehicleModel;

public interface VehicleDao {

	VehicleModel getVehicle();

	List<VehicleModel> getAllVehicles();

	void deleteVehicle(int vehicleId);

	void updateVehicle(VehicleModel vehicle);

	void insertVehicle(VehicleModel vehicle);

	ResultSet getCatalog();

	ResultSet getSearchResults(String keyword);
	

}
