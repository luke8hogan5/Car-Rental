package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderModel{
<<<<<<< HEAD
	private  int user_id;
	private  int vehicle_id;
	private int orderStatus;
	private boolean paymentCleared;
	private Date dateCreated;
	private Date dateDue;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
=======
	private  int userId;
	private  int vehicleId;
	private Date dateCreated; // dateCreated
	private int rentDuration;
	private boolean paymentCleared;
	private boolean vehicleReturned;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
>>>>>>> 07e6414a0b18a0340c27864332cceeba6b62de17

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