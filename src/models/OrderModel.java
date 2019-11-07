package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderModel{
	private int orderstatus;
	private boolean paymentCleared;
	private Date d1; // dateCreated
	private Date d2; //Duedate
	
	public OrderModel(int orderstatus, boolean paymentCleared, Date d1, Date d2){
		this.orderstatus = orderstatus;
		this.paymentCleared = paymentCleared;
		this.d1 = d1;
		this.d2 = d2;
	}
	public OrderModel(int orderstatus,boolean paymentCleared){
		this.orderstatus = orderstatus;
		this.paymentCleared = paymentCleared;
	}
	
	public int getOrderstatus(){
		return orderstatus;
	}
	
	public void setOrderstatus(int orderstatus){
		this.orderstatus = orderstatus;
	}
	
	public boolean getPaymentCleared(){
		return paymentCleared;
	}
	
	public void setPaymentCleared(boolean paymentCleared){
		this.paymentCleared = paymentCleared;
	}
	
//	public String getCreatedDate(){
//		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//		Date d1 = format.parse(d1String);
//		Date d1 = new Date();
//		return d1;
//	}
	
	public void setCreatedDate(Date d1){
		this.d1 = d1;
	}
	
//	public String getDueDate(){
//		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
////		Date d2 = format.parse(d2String);
//		Date d2 = new Date();
//		return d2;
//	}
	
	public void setDueDate(Date d2){
		this.d2 = d2;
	}
}