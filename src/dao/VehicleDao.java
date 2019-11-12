package dao;

import java.util.List;

import models.VehicleModelAdm;

public interface VehicleDao {

	VehicleModelAdm getVehicle();

	List<VehicleModelAdm> getAllVehicles();

	void insertVehicle(VehicleModelAdm vehicle);

	void updateVehicle(VehicleModelAdm vehicle);

	void deleteVehicle(int vehicleId);

}
