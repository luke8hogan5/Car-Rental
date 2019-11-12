package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderModel{
	private  int userId;
	private  int vehicleId;
	private Date dateCreated; // dateCreated
	private int rentDuration;
	private boolean paymentCleared;
	private boolean vehicleReturned;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

	public OrderModel(int userId,int vehicleId,int rentDuration,boolean paymentCleared){
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.paymentCleared = paymentCleared;
		this.rentDuration = rentDuration;
	}

	public boolean getPaymentCleared(){
		return paymentCleared;
	}
	
	public void setPaymentCleared(boolean paymentCleared){
		this.paymentCleared = paymentCleared;
	}
	
	public String getCreatedDate(){ return dateFormat.format(dateCreated); }
	
	public void setCreatedDate(Date d1){
		this.dateCreated = d1;
	}
	
	public int getRentDuration(){
		return this.rentDuration;
	}
	
	public void setDueDate(int rentDuration){
		this.rentDuration = rentDuration;
	}

	public int getUserId() { return userId; }

	public void setUserId(int userId) { this.userId = userId; }

	public int getVehicleId() { return vehicleId; }

	public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

	public boolean isVehicleReturned() { return vehicleReturned; }

	public void setVehicleReturned(boolean vehicleReturned) { this.vehicleReturned = vehicleReturned; }
}