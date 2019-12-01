package controllers;

import daoImpl.OrderDaoImpl;
import interfaces.PaymentListener;
import models.OrderModel;
import models.PaymentModel;

import javax.swing.*;

public class PaymentController implements PaymentListener {

	private OrderDaoImpl dao = new OrderDaoImpl();

	public PaymentController() {
	}

   /**
	* Dummy payment system that checks the format of entered card details. If details do not follow
	* format user will be prompted to reenter payment details.
	*/
	@Override
	public void paymentPerformed(PaymentModel event, OrderModel newOrder) {
		assert(event != null & newOrder != null);
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
			JOptionPane.showMessageDialog(null,"Payment Processed,\nThank you for your business.","Payment Succesful",1);
			dao.updatePayment(newOrder.getOrderId());
		}
		else {
			JOptionPane.showMessageDialog(null,"Card Details Invalid","Error",1);
			System.out.print("Card Invalid, try again");
		}
	}

}
