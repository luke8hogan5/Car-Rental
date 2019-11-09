package models;

public class OrderModelAdm {

	private String dateCreated;
	private String dateDue;
	private String paymentCleared;
	
	public OrderModelAdm(String dateCreated, String dateDue, String paymentCleared) {
		super();
		this.dateCreated = dateCreated;
		this.dateDue = dateDue;
		this.paymentCleared = paymentCleared;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateDue() {
		return dateDue;
	}
	public void setDateDue(String dateDue) {
		this.dateDue = dateDue;
	}
	public String getPaymentCleared() {
		return paymentCleared;
	}
	public void setPaymentCleared(String paymentCleared) {
		this.paymentCleared = paymentCleared;
	}
}
