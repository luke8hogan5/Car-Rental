package views;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.*;

import controllers.PaymentController;
import interfaces.PaymentListener;
import models.OrderModel;
import models.PaymentModel;

public class PaymentView extends JPanel {
	private MasterView parent;
	private JButton payButton, backbtn;
	private JTextField cardHolder;
	private JTextField cardNo;
	private JTextField expDate;
	private JTextField cVV;

	private PaymentListener paymentListener;
		
	public PaymentView(MasterView parent, OrderModel newOrder) {
		super();
		this.parent = parent;
		paymentListener = new PaymentController();
		
		cardHolder = new JTextField(30);
		cardNo = new JTextField(16);
		cVV = new JPasswordField(3);
		expDate = new JTextField(6);
		payButton = new JButton("Process Payment");
        backbtn = new JButton("Back");

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 0);


        backbtn.setBounds(0,180,80,30);
        add(backbtn,gc);

        backbtn.addActionListener(ae -> parent.changePanel(new CatalogView(parent)));
		
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(100, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;

		add(new JLabel("Card Holder Name: "), gc);

		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(100, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;

		add(cardHolder, gc);

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;

		add(new JLabel("Card Number: "), gc);

		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;

		add(cardNo, gc);

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("Expiry Date: "), gc);
		
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;

		add(expDate, gc);
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 4;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("CVV:  "), gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 4;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		
		add(cVV, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 5;
		gc.weightx = 1;
		gc.weighty = 100;
		gc.fill = GridBagConstraints.NONE;

		add(payButton, gc);

		payButton.addActionListener(e->{
			String cardHolderField = cardHolder.getText();
			String cardNoField = cardNo.getText();
			String expDateField = expDate.getText();
			int cVVField = Integer.parseInt(cVV.getText());
			//userId = get id on login from userModel
	        parent.changePanel(new CatalogView(parent));

			try {
				firePaymentEvent(new PaymentModel(cardHolderField, cardNoField, cVVField, expDateField),newOrder);
			} catch (SQLException e1) {
				System.out.print("Could not process payment details");
				e1.printStackTrace();
			}
		});
		}

		public void firePaymentEvent(PaymentModel event,OrderModel newOrder) throws SQLException {
			if (paymentListener != null) {
				paymentListener.paymentPerformed(event,newOrder);
			}
		}
}
