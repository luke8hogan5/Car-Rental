package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderModel{
	private  int userId;
	private  int vehicleId;
	private  int orderId;
	private Date dateCreated;
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
	
	public int getRentDuration(){
		return this.rentDuration;
	}

	public int getUserId() { return userId; }

	public int getVehicleId() { return vehicleId; }

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}