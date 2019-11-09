package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import database.Database;
import interfaces.OrderListener;
import models.VehicleModel;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class OrderView extends MasterView implements ActionListener {


	private OrderListener OrderListener;
	public String[] vehicleModels = new String[10];
	public JComboBox modelList;

	public OrderView() {
		super();
				String id = "" , vMake = "" , vModel = "" ;
				double vPrice = 0.0;
				Connection conn = Database.getConnection();
				String query = "SELECT `vehicle_id` FROM  `vehicle`;";
				Statement st = null;
				try {
					st = conn.createStatement();
					ResultSet rs = st.executeQuery(query);
					int i = 0;
					while (rs.next()) {
						id = rs.getString("vehicle_id");
						System.out.println(rs.getString("vehicle_id"));
						i++;
					}
					buildTable(id);
				}catch (SQLException exception) {
					exception.printStackTrace();
				}
			}


	public void buildTable(String id) {

		System.out.println(id);

		JTextField idBox = new JTextField(3);
		idBox.setText(id);
		JButton submitId = new JButton("Get Details");
		idBox.setSize(50,20);
	    JButton getCosts = new JButton("Get Prices");
		getCosts.setEnabled(false);
		/*JComboBox makeBox = new JComboBox();
		makeBox.setModel(new DefaultComboBoxModel(vehicleMakes));
		JComboBox makeList = makeBox;
		*/
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 0, 30, 0);
		add(new JLabel("Place Your Order"), gc);

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Vehicle Reference Number :"), gc);

		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 10, 0);
        add(idBox, gc);

		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 3;
		gc.gridy = 1;
		gc.weightx = 0.5;
		gc.weighty = 1;
        gc.insets = new Insets(50, 10, 10, 100);
		add(submitId, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Vehicle Make"), gc);

		JTextField makeBox = new JTextField(10);
        makeBox.setSize(100,20);
        makeBox.setEditable(false);
        //makeBox.setVisible(false);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 10, 10);
		add(makeBox, gc);
        //makeBox.setVisible(false);


        //makeBox.setVisible(false);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 0.4;
		gc.insets = new Insets(10, 10, 50, 10);
		add(new JLabel("Vehicle Model"), gc);

		JTextField modelBox = new JTextField(10);
        modelBox.setSize(100,10);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 0.3;
		gc.insets = new Insets(10, 10, 50, 10);
		add(modelBox,gc);
        modelBox.setEditable(false);

		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 0.3;
		gc.insets = new Insets(10, 10, 10, 10);
		add(new JLabel("Vehicle Year"), gc);

		JTextField yearBox = new JTextField(10);
		yearBox.setSize(100,10);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 0.4;
		gc.insets = new Insets(10, 10, 10, 10);
		add(yearBox,gc);
		yearBox.setEditable(false);

		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 3;
		gc.gridy = 4;
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.insets = new Insets(10, 10, 10, 100);
        add(getCosts, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 5;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Vehicle Price"), gc);

        JTextField priceBox = new JTextField(10);
        //priceBox.setSize(200,20);
        priceBox.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 0);
        add(priceBox, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Rent Price Per Day :"), gc);

        JTextField rentBox = new JTextField(10);
        //priceBox.setSize(200,20);
        rentBox.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 0);
        add(rentBox, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 7;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Lease Price Per Year :"), gc);

        JTextField leaseBox = new JTextField(10);
        //priceBox.setSize(200,20);
        leaseBox.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 7;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 0);
        add(leaseBox, gc);

        JButton proPay = new JButton("Payment");
        proPay.setEnabled(false);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 3;
		gc.gridy = 7;
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.insets = new Insets(50, 10, 10, 100);
        add(proPay, gc);


        submitId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idBox.getText();
				String vMake = "" , vModel = "" , vYear = "" ;
				double vPrice = 0.0;

				Connection conn = Database.getConnection();
				Statement st = null;
				String infoQuery = "SELECT `vehicleMake`,`vehicleModel`,`vehiclePrice`,`vehicleYear` FROM  `vehicle` WHERE `vehicle_id`=\""+id+"\";";;
				ResultSet rq = null;
				try {
					st = conn.createStatement();
					rq = st.executeQuery(infoQuery);
					while(rq.next()){
						vMake = rq.getString("vehicleMake");
						vModel = rq.getString("vehicleModel");
						vYear = rq.getString("vehicleYear");
					}
					makeBox.setText(vMake);
					modelBox.setText(vModel);
					yearBox.setText(vYear);

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				getCosts.setEnabled(true);
			}
		} );
        getCosts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idBox.getText();
                double vPrice = 0.0;
                double vRent = 0.00 , vLease = 0.00;

                Connection conn = Database.getConnection();
                Statement st = null;
                String infoQuery = "SELECT `vehiclePrice` FROM  `vehicle` WHERE `vehicle_id`=\""+id+"\";";;
                ResultSet rq = null;
                try {
                    st = conn.createStatement();
                    rq = st.executeQuery(infoQuery);
                    while(rq.next()){
                        vPrice = rq.getDouble("vehiclePrice");
                        //System.out.println(vMake);
                        //System.out.println(vMake);
                    }
                    vRent = ((vPrice / 365)/2);
                    BigDecimal bd = new BigDecimal(vRent).setScale(2, RoundingMode.HALF_UP);
                    vLease = vPrice/3;
                    BigDecimal bd1 = new BigDecimal(vLease).setScale(2, RoundingMode.HALF_UP);

                    priceBox.setText(""+vPrice);
                    priceBox.setVisible(true);
                    rentBox.setText(""+bd.doubleValue());
                    leaseBox.setText(""+bd1.doubleValue());

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                proPay.setEnabled(true);
            }
        } );
        proPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 // Move Frame too OrderViewExt
            }
        } );

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Actions Yeet");
	}
////			fireLoginEvent(new RegisterModel(name, password));
////		} else {
////			JOptionPane.showMessageDialog(this, "User not found.",
////					"Error", JOptionPane.WARNING_MESSAGE);
////		}
////	}

	public void setOrderListener(OrderListener orderListener) {
		this.OrderListener = orderListener;
	}

	public void fireLoginEvent(VehicleModel event) throws SQLException {
		if (OrderListener != null) {
			OrderListener.orderPerformed(event);
		}
	}
}
