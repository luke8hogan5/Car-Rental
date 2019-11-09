package models;

public class CatalogModel {
		
		private String VehicleMake;
		private String VehicelModel;
		private double VehiclePrice;
		private int VehicleYear;
		
		//vehicleMake, vehiclePrice,vehicelModel,vehicleYear
		
		public CatalogModel(String VehicleMake, String VehicelModel, double VehiclePrice,int VehicleYear){
			this.VehicleMake = VehicleMake;
			this.VehicelModel = VehicelModel;
			this.VehiclePrice = VehiclePrice;
		}
		
		public String getVehicleMake(){
			return VehicleMake;
		}
		
		public void setVehicleMake(String VehicleMake){
			this.VehicleMake = VehicleMake;
		}
		
		public String getVehicelModel(){
			return VehicelModel;
		}
		
		public void setVehicelModel(String VehicelModel){
			this.VehicelModel =VehicelModel;
		}
		
		public double getVehiclePrice(){
			return VehiclePrice;
		}
		
		public void setVehiclePrice(double VehiclePrice){
			this.VehiclePrice = VehiclePrice;
		}
		
		public int getVehicleYear(){
			return VehicleYear;
		}
		
		public void setVehicleYear(int VehicleYear){
			this.VehicleYear = VehicleYear;
		}
	}