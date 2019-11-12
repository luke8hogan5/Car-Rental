package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import database.Database;
import interfaces.PaymentListener;
import models.OrderModel;
import models.PaymentModel;
import views.MasterView;
import views.PaymentView;

import javax.swing.*;

public class PaymentController implements PaymentListener {
		
	private PaymentView view;
		
	public PaymentController(PaymentView view) {
			this.view = view;
		}

		@Override
		public void paymentPerformed(PaymentModel event, OrderModel newOrder) throws SQLException {
			System.out.println("Payment process event received: " + event.getCardHolder() + "; " + 
						event.getCardNo() + "; " +  event.getExpDate() + "; " + event.getcVV());
		        
			String patternNo = "[0-9]{4}([-]?[0-9]{4}){3}";
			String patternCvv = "[0-9]{3}";
			String patternExpiry = "[0-9]{2}[/][0-9]{2}";

			String cardNo = event.getCardNo();
			String cardCvv = Integer.toString(event.getcVV());
			String cardExp = event.getExpDate();

//			cardNo = cardNo.replaceAll("-", "");
			
				if(cardNo.matches(patternNo)&&cardCvv.matches(patternCvv)&&cardExp.matches(patternExpiry)){
			    	System.out.println("Card is valid\nPayment has been processed");
				}
				else {
					JOptionPane.showMessageDialog(null,"Card Details Invalid","Error",1);
					System.out.print("Card Invalid, try again");
				}   
		}

}
