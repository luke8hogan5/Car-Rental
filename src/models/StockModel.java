import java.util.ArrayList;


public class StockModel{
	
    private ArrayList<Vehicle> stock = new ArrayList<Vehicle>();
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
	public void addVehicle(Vehicle vehicle){
		
        this.stock.add(vehicle);
    }
    
    // remove vehicle in stock list
    public void removeVehicle(Vehicle vehicle){
    	this.stock.remove(vehicle);
    }
    
    // print vehicles
    public void getVehicles() {
    if (stock != null) {
        for (Vehicle v : stock) {
            System.out.println(v);
        	}
    	}
    }
}
    
	