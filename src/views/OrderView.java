package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import javax.swing.*;

import controllers.OrderController;
import interfaces.OrderListener;
import models.VehicleModel;

public class OrderView extends JPanel implements ActionListener {
	private MasterView parent;

	private OrderListener orderListener;
	
	private JButton backbtn;

	public OrderView(MasterView parent, VehicleModel data) {
		super();
		this.parent = parent;
		orderListener = new OrderController(this);
		String vMake = "" , vModel = "" , vYear = "" ;

		vMake = data.getVehicleMake();
		vModel = data.getVehicleModel();
		vYear = Integer.toString(data.getVehicleYear());

		buildTable(vMake,vModel,vYear,data);
	}


	public void buildTable(String vMake,String vModel,String vYear,VehicleModel data) {

		JButton getCosts = new JButton("Get Prices");
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
		
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 0, 30, 0);
		add(new JLabel("Place Your Order"), gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Vehicle Make"), gc);

		JTextField makeBox = new JTextField(10);
        makeBox.setSize(100,20);
        makeBox.setEditable(false);
		makeBox.setText(vMake);
		//makeBox.setVisible(false);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 10, 10);
		add(makeBox, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Vehicle Model"), gc);

		JTextField modelBox = new JTextField(10);
        modelBox.setSize(100,10);
        modelBox.setText(vModel);
		modelBox.setEditable(false);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 10, 10);
		add(modelBox,gc);

		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 80, 10);
		add(new JLabel("Vehicle Year"), gc);

		JTextField yearBox = new JTextField(10);
		yearBox.setSize(100,10);
		yearBox.setEditable(false);
		yearBox.setText(vYear);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 80, 10);
		add(yearBox,gc);

		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 3;
		gc.gridy = 3;
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 50, 100);
        add(getCosts, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 4;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Vehicle Price"), gc);

        JTextField priceBox = new JTextField(10);
        //priceBox.setSize(200,20);
        priceBox.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 4;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 0);
        add(priceBox, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 5;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Rent Price Per Day :"), gc);

        JTextField rentBox = new JTextField(10);
        //priceBox.setSize(200,20);
        rentBox.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 0);
        add(rentBox, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Enter Rent Duration In Days:"), gc);

        JTextField durationBox = new JTextField(10);
        durationBox.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 0);
        add(durationBox, gc);

        JButton proPay = new JButton("Price Calculation");
        proPay.setEnabled(false);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 3;
		gc.gridy = 6;
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.insets = new Insets(50, 10, 10, 100);
        add(proPay, gc);

        getCosts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double vPrice = 0.00 ,vRent = 0.00 , vLease = 0.00;
				vPrice = data.getVehiclePrice();

				vRent = ((vPrice / 365)/2);
				BigDecimal bd = new BigDecimal(vRent).setScale(2, RoundingMode.HALF_UP);
				//vLease = vPrice/3;
				//BigDecimal bd1 = new BigDecimal(vLease).setScale(2, RoundingMode.HALF_UP);

				priceBox.setText(""+vPrice);
				priceBox.setVisible(true);
				rentBox.setText(""+bd.doubleValue());
				//leaseBox.setText(""+bd1.doubleValue());
				durationBox.setEditable(true);
                proPay.setEnabled(true);
            }
        } );

        proPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				int rentDuration = Integer.parseInt(durationBox.getText());
            	parent.changePanel(new OrderViewExt(parent,rentDuration,data));
            	 // Move Frame to OrderViewExt
            }
        } );

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Actions Yeet");
	}

	public void fireLoginEvent(VehicleModel event) throws SQLException {
		if (orderListener != null) {
			orderListener.orderPerformed(event);
		}
	}
}
