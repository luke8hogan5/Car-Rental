package controllers;

import java.sql.*;
import java.util.regex.Pattern;

import dao.OrderDao;
import daoImpl.OrderDaoImpl;
import database.Database;
import interfaces.PaymentListener;
import models.OrderModel;
import models.PaymentModel;
import views.MasterView;
import views.PaymentView;
import dao.OrderDao;

import javax.swing.*;

public class PaymentController implements PaymentListener {
		
	private PaymentView view;
	private OrderDaoImpl dao = new OrderDaoImpl();

	public PaymentController(PaymentView view) {
			this.view = view;
		}

		@Override
		public void paymentPerformed(PaymentModel event, OrderModel newOrder) throws SQLException {
			System.out.println("Payment process event received: " + event.getCardHolder() + "; " + 
						event.getCardNo() + "; " +  event.getExpDate() + "; " + event.getcVV());
			System.out.print(newOrder.getOrderId());
		        
			String patternNo = "[0-9]{4}([-]?[0-9]{4}){3}";
			String patternCvv = "[0-9]{3}";
			String patternExpiry = "[0-9]{2}[/][0-9]{2}";

			String cardNo = event.getCardNo();
			String cardCvv = Integer.toString(event.getcVV());
			String cardExp = event.getExpDate();

            if(cardNo.matches(patternNo)&&cardCvv.matches(patternCvv)&&cardExp.matches(patternExpiry)){
                JOptionPane.showMessageDialog(null,"Payment Processed \n You will earn 1 loyalty point per day rented.","Payment Succesful",1);
                dao.updatePayment(newOrder.getOrderId());
            }
            else {
                JOptionPane.showMessageDialog(null,"Card Details Invalid","Error",1);
                System.out.print("Card Invalid, try again");
            }
		}

}
