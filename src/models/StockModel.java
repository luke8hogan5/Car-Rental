package models;

import java.util.ArrayList;


public class StockModel{
	
    private ArrayList<VehicleModel> stock = new ArrayList<VehicleModel>();
	private double stockValue;
	private int stockAvailability;
	
	public StockModel(double stockValue, int stockAvailability){
		this.stockValue = stockValue;
		this.stockAvailability = stockAvailability;
	}
	
	public double getStockValue(){
		return stockValue;
	}
	
	public void setStockValue(double stockValue){
		this.stockValue = stockValue;
	}
	
	public int getStockAvailability(){
		return stockAvailability;
	}
	
	public void setStockAvailability(int stockAvailability){
		this.stockAvailability = stockAvailability;
	}

	// add vehicle in stock list
	public void addVehicle(VehicleModel vehicle){
		
        this.stock.add(vehicle);
    }
    
    // remove vehicle in stock list
    public void removeVehicle(VehicleModel vehicle){
    	this.stock.remove(vehicle);
    }

    // print vehicles
    public void getVehicles() {
    if (stock != null) {
        for (VehicleModel v : stock) {
            System.out.println(v);
        	}
    	}
    }
}
    
	