package models;

public class VehicleModelAdm {
	private int vehicleId;
	private int vehicleType;
	private String vehicleMake;
	private String vehicleModel;
	
	public VehicleModelAdm(int vehicleId) {
		super();
		this.vehicleId = vehicleId;
	}
	
	public VehicleModelAdm(int vehicleId, String vehicleModel, String vehicleMake, int vehicleYear, double vehiclePrice, int available) {
		super();
		this.vehicleId = vehicleId;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleMake() {
		return vehicleMake;
	}
	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
}
