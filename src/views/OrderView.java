package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controllers.OrderController;
import interfaces.OrderListener;
import models.VehicleModel;

import static java.lang.String.valueOf;

public class OrderView extends JPanel {
	private MasterView parent;

	private OrderListener orderListener;
	
	private JButton backbtn;

	public OrderView(MasterView parent, VehicleModel data) {
		super();
		this.parent = parent;
		orderListener = new OrderController();
		buildTable(data);
	}

	public void buildTable(VehicleModel data) {

		backbtn = new JButton("Back");
		setLayout(new GridBagLayout());

		int userId = parent.getCurrentUser().getUserId();
		int vehicleId = data.getVehicleId();
		int rating = parent.getCurrentUser().getLoyaltyRating();
		double discount = (orderListener.totalDiscounts(rating));

		JTextField priceBox = new JTextField(10);
		JButton proPay = new JButton("Proceed to payment");
		proPay.setEnabled(false);
		JTextField durationBox = new JTextField(10);

		backbtn = new JButton("Back");
		backbtn.setBounds(0,180,80,30);
		backbtn.addActionListener(ae -> parent.changePanel(new CatalogView(parent)));

		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		add(backbtn,gc);

		gc.anchor = GridBagConstraints.NORTH;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.insets = new Insets(10, 0, 30, 0);
		add(new JLabel("Place Your Order"), gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Rent Price Per Day :"), gc);

		JTextField rentBox = new JTextField(10);
		rentBox.setEditable(false);
		rentBox.setText(Double.toString(data.getVehiclePrice()));

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.insets = new Insets(10, 10, 10, 10);
		add(rentBox, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 3;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Your Loyalty Rating : "), gc);

		JTextField loyaltyBox = new JTextField(valueOf(rating), 5);
		loyaltyBox.setEditable(false);

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 4;
		gc.insets = new Insets(10, 0, 10, 0);
		add(loyaltyBox, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Enter Rent Duration In Days:"), gc);

//		Disable proBtn if any of fields are empty
		durationBox.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(durationBox.getText().matches("[0-9]+")) {
					int duration = Integer.parseInt(durationBox.getText());
					priceBox.setText(String.format("%.2f", rentForDuration(data, duration, discount)));
					proPay.setEnabled(true);
				}else {
					priceBox.setText("");
					proPay.setEnabled(false);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(durationBox.getText().matches("[0-9]+")) {
					int duration = Integer.parseInt(durationBox.getText());
					priceBox.setText(String.format("%.2f", rentForDuration(data, duration, discount)));
					proPay.setEnabled(true);
				}else {
					priceBox.setText("");
					proPay.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(durationBox.getText().matches("[0-9]+")) {
					int duration = Integer.parseInt(durationBox.getText());
					priceBox.setText(String.format("%.2f", rentForDuration(data, duration, discount)));
					proPay.setEnabled(true);
				}else {
					priceBox.setText("");
					proPay.setEnabled(false);
				}
			}
		});

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.insets = new Insets(10, 10, 10, 0);
		add(durationBox, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 3;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Discount: "), gc);

		JTextField discountBox = new JTextField(10);
		discountBox.setText(String.format("%.2f", discount));
		discountBox.setEditable(false);

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 4;
		gc.insets = new Insets(10, 0, 10, 0);
		add(discountBox, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 3;
		gc.gridy = 4;
		gc.insets = new Insets(10, 0, 10, 0);
		add(new JLabel("Additonal Fees"), gc);

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 3;
		gc.gridy = 5;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Deposit Fee : "), gc);

		JTextField depositBox = new JTextField("50", 10);
		depositBox.setEditable(false);
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 4;
		gc.insets = new Insets(10, 0, 10, 0);
		add(depositBox, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 6;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Vehicle Price"), gc);

		priceBox.setSize(200,20);
		priceBox.setEditable(false);

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.insets = new Insets(10, 10, 10, 0);
		add(priceBox, gc);

		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 3;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Delivery Fee : "), gc);

		JTextField deliveryBox = new JTextField(13);
		deliveryBox.setEditable(false);
		if (rating >= 100) {
			deliveryBox.setText("Free Based On Loyalty");
		}else{
			deliveryBox.setText("50.00");
		}

		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 4;
		gc.insets = new Insets(10, 0, 10, 10);
		add(deliveryBox, gc);

        proPay.addActionListener(e -> {
			try{
			int rentDuration = Integer.parseInt(durationBox.getText());
			fireOrderSubmitted(userId,vehicleId,rentDuration);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	});

		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 3;
		gc.gridy = 7;
		gc.insets = new Insets(50, 10, 10, 100);
        add(proPay, gc);
	}

	private void fireOrderSubmitted(int userId, int vehicleId, int rentDuration) throws SQLException {
			orderListener.orderSubmited(userId, vehicleId,rentDuration,false);
			parent.changePanel(new PaymentView(parent, orderListener.getNewOrder()));
	}

	private double rentForDuration(VehicleModel data, int duration, Double discount){
		double vRent = data.getVehiclePrice();
		vRent *= duration;
		vRent -= vRent * discount;
		BigDecimal bD = new BigDecimal(vRent).setScale(2, RoundingMode.HALF_UP); // Round price up to 2 decimal places

		return bD.doubleValue();
	}
}
