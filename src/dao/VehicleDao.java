package dao;

import java.util.List;

import models.VehicleModel;

public interface VehicleDao {

	VehicleModel getVehicle();

	List<VehicleModel> getAllVehicles();

	void insertVehicle(VehicleModel vehicle);

	void updateVehicle(VehicleModel vehicle);

	void deleteVehicle(int vehicleId);

}
