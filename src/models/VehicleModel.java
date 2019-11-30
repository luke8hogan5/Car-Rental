package models;

public class VehicleModel{

	private int vehicleId;
	private String vehicleType;
	private String vehicleMake;
	private double vehiclePrice;
	private String vehicleModel;
	private int vehicleYear;
	private int vehicleAvailability;

	public VehicleModel(int vehicleId, String vehicleType, String vehicleMake, double vehiclePrice, String vehicleModel, int vehicleYear) {
		this.vehicleId = vehicleId;
		this.vehicleType = vehicleType;
		this.vehicleMake = vehicleMake;
		this.vehiclePrice = vehiclePrice;
		this.vehicleModel = vehicleModel;
		this.vehicleYear = vehicleYear;
	}
	
	public String getVehicleModel(){
		return vehicleModel;
	}
	
	public double getVehiclePrice(){
		return vehiclePrice;
	}

	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}
	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public int getVehicleYear() {
		return vehicleYear;
	}
	public void setVehicleYear(int vehicleYear) {
		this.vehicleYear = vehicleYear;
	}

	public int getVehicleAvailability() {
		return vehicleAvailability;
	}

	public void setVehicleAvailability(int vehicleAvailability) {
		this.vehicleAvailability = vehicleAvailability;
	}

}