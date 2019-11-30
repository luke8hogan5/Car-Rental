package models;

public class PaymentModel {
	private String cardHolder;
	private String cardNo;
	private int cVV;
	private String expDate;
	
	public PaymentModel(String cardHolder, String cardNo, int cVV, String expDate) { //include userId later
		super();
		this.cardHolder = cardHolder;
		this.cardNo = cardNo;
		this.cVV = cVV;
		this.expDate = expDate;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public String getCardNo() {
		return cardNo;
	}

	public int getcVV() {
		return cVV;
	}

	public String getExpDate() {
		return expDate;
	}
}
