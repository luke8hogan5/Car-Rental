package models;

public class VehicleModel{
	
	private String type;
	private String make;
	private String model;
	private String details;
	private double price;
	private int year;

	
	public VehicleModel(String VehicleType, String VehicleDetails, double VehiclePrice){
		this.type = VehicleType;
		this.details = VehicleDetails;
		this.price = VehiclePrice;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDetails(){
		return details;
	}
	
	public void setDetails(String details){
		this.details =details;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}