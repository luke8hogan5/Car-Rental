public class VehicleModel{
	
	private String VehicleType;
	private String VehicleDetails;
	private double VehiclePrice;
	
	public VehicleModel(String VehicleType, String VehicleDetails, double VehiclePrice){
		this.VehicleType = VehicleType;
		this.VehicleDetails = VehicleDetails;
		this.VehiclePrice = VehiclePrice;
	}
	
	public String getVehicleType(){
		return Vehicletype;
	}
	
	public void setVehicleType(String VehicleType){
		this.VehicleType = VehicleType;
	}
	
	public String getVehicleDetails(){
		return VehicleDetails;
	}
	
	public void setVehicleDetails(String VehicleDetails){
		this.VehicleDetails =VehicleDetails;
	}
	
	public double getVehiclePrice(){
		return VehiclePrice;
	}
	
	public void setVehiclePrice(double VehiclePrice){
		this.VehiclePrice = VehiclePrice;
	}
}