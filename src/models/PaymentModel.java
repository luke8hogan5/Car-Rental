package models;

public class PaymentModel {
	private int userId;
	private String cardHolder;
	private String cardNo;
	private int cVV;
	private String expDate;
	
	public PaymentModel(String cardHolder, String cardNo, int cVV, String expDate) { //include userId later
		super();
		this.userId = userId;
		this.cardHolder = cardHolder;
		this.cardNo = cardNo;
		this.cVV = cVV;
		this.expDate = expDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public int getcVV() {
		return cVV;
	}
	public void setcVV(int cVV) {
		this.cVV = cVV;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
}
