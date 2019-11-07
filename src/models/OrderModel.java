package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderModel{
	private int orderStatus;
	private boolean paymentCleared;
	private Date dateCreated; // dateCreated
	private Date dateDue; //Duedate
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public OrderModel(int orderstatus, boolean paymentCleared, Date dateCreated, Date dateDue){
		this.orderStatus = orderstatus;
		this.paymentCleared = paymentCleared;
		this.dateCreated = dateCreated;
		this.dateDue = dateDue;
	}
	public OrderModel(int orderstatus,boolean paymentCleared){
		this.orderStatus = orderstatus;
		this.paymentCleared = paymentCleared;
	}
	
	public int getOrderStatus(){
		return orderStatus;
	}
	
	public void setOrderStatus(int orderStatus){
		this.orderStatus = orderStatus;
	}
	
	public boolean getPaymentCleared(){
		return paymentCleared;
	}
	
	public void setPaymentCleared(boolean paymentCleared){
		this.paymentCleared = paymentCleared;
	}
	
	public String getCreatedDate(){
		return dateFormat.format(dateCreated);
	}
	
	public void setCreatedDate(Date d1){
		this.dateCreated = d1;
	}
	
	public String getDueDate(){
		return dateFormat.format(dateDue);
	}
	
	public void setDueDate(Date d2){
		this.dateDue = d2;
	}
}