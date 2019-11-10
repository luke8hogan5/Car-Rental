package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import database.Database;
import interfaces.PaymentListener;
import models.PaymentModel;
import views.PaymentView;

public class PaymentController implements PaymentListener {
		
	private PaymentView view;
		
	public PaymentController(PaymentView view) {
			this.view = view;
		}

		@Override
		public void paymentPerformed(PaymentModel event) throws SQLException {
			System.out.println("Payment process event received: " + event.getCardHolder() + "; " + 
						event.getCardNo() + "; " +  event.getExpDate() + "; " + event.getcVV());
		        
			String pattern = "[0-9-]{16,20}";
			
			String cardNo = event.getCardNo();
			cardNo = cardNo.replaceAll("-", "");
			
				if(cardNo.matches(pattern)){
			    	System.out.println("Card is valid\nPayment has been processed");
				}
				else {
					System.out.print("Card Invalid, try again");
				}   
		}

}
