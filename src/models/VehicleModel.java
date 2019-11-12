package models;

public class VehicleModel{

	private int vehicleId;
	private String vehicleType;
	private String vehicleMake;
	private double vehiclePrice;
	private String vehicleModel;
	private int vehicleYear;

	public VehicleModel(int vehicleId, String vehicleType, String vehicleMake, double vehiclePrice, String vehicleModel, int vehicleYear) {
		this.vehicleId = vehicleId;
		this.vehicleType = vehicleType;
		this.vehicleMake = vehicleMake;
		this.vehiclePrice = vehiclePrice;
		this.vehicleModel = vehicleModel;
		this.vehicleYear = vehicleYear;
	}

	public String getVehicleType(){
		return vehicleType;
	}
	public void setVehicleType(String VehicleType){
		this.vehicleType = VehicleType;
	}
	
	public String getVehicleModel(){
		return vehicleModel;
	}
	public void setVehicleModel(String VehicleDetails){
		this.vehicleModel =VehicleDetails;
	}
	
	public double getVehiclePrice(){
		return vehiclePrice;
	}
	public void setVehiclePrice(double VehiclePrice){
		this.vehiclePrice = VehiclePrice;
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
}