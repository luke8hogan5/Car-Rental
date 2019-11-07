package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import database.Database;
import interfaces.OrderListener;
import models.VehicleModel;

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

		JTextField idBox = new JTextField(10);
		idBox.setText(id);
		JButton submitId = new JButton("Get Details");
		idBox.setSize(50,20);
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
		gc.weighty = 0.2;
		gc.insets = new Insets(10, 0, 50, 0);
		add(new JLabel("Place Your Order"), gc);

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0.2;
		gc.weighty = 0.2;
		gc.insets = new Insets(10, 0, 0, 10);
		add(new JLabel("Vehicle Reference Number :"), gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 0;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.insets = new Insets(70, 10, 0, 0);
		add(idBox, gc);

		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		add(submitId, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 0.2;
		gc.weighty = 0.2;
		gc.insets = new Insets(10, 0, 0, 0);
		add(new JLabel("Vehicle Make"), gc);

		JTextField makeBox = new JTextField(3);
		makeBox.setVisible(true);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 0.2;
		gc.weighty = 0.2;
		gc.insets = new Insets(0, 0, 0, 0);
		add(makeBox, gc);

		makeBox.setVisible(false);
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 0.2;
		gc.weighty = 0.2;
		gc.insets = new Insets(10, 0, 0, 0);
		add(new JLabel("Vehicle Model"), gc);

		JTextField modelBox = new JTextField(3);
		modelBox.setVisible(false);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 0.2;
		gc.weighty = 0.2;
		gc.insets = new Insets(0, 0, 0, 0);
		add(modelBox,gc);

		gc.anchor = GridBagConstraints.PAGE_START;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 0.2;
		gc.weighty = 0.2;
		gc.insets = new Insets(10, 0, 0, 0);
		add(new JLabel("Vehicle Price"), gc);

		JTextField priceBox = new JTextField(3);
		makeBox.setVisible(true);
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 3;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.insets = new Insets(0, 0, 0, 0);
		add(priceBox, gc);

		submitId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idBox.getText();
				String vMake = "" , vModel = "" ;
				double vPrice = 0.0;

				Connection conn = Database.getConnection();
				Statement st = null;
				String infoQuery = "SELECT `vehicleMake`,`vehicleModel`,`vehiclePrice` FROM  `vehicle` WHERE `vehicle_id`=\""+id+"\";";;
				ResultSet rq = null;
				try {
					st = conn.createStatement();
					rq = st.executeQuery(infoQuery);
					while(rq.next()){
						vMake = rq.getString("vehicleMake");
						vModel = rq.getString("vehicleModel");
						vPrice = rq.getDouble("vehiclePrice");
						System.out.println(vMake);
					}
					makeBox.setText(vMake);
					makeBox.setVisible(true);
					modelBox.setText(vModel);
					modelBox.setVisible(true);
					priceBox.setText(""+vPrice);
					priceBox.setVisible(true);

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} );


		/*gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		add(new JLabel("Vehicle Make: "), gc);

		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		add(makeList, gc);

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		JLabel modelLabel = new JLabel("Vehicle Model :");
		modelLabel.setVisible(false);
		add(modelLabel, gc);


		JComboBox modelList = new JComboBox(vehicleModels);
		modelList.setVisible(false);

		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 10, 10, 0);
		gc.fill = GridBagConstraints.NONE;
		add(modelList, gc);
		*/
		/*makeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vehicleModel = makeList.getSelectedItem().toString();


				Connection conn = Database.getConnection();
				String query = "SELECT `vehicleModel` FROM  `vehicle` WHERE `vehicleMake`=\""+vehicleModel+"\";";
				Statement st = null;
				try {
					st = conn.createStatement();
					ResultSet rs = st.executeQuery(query);
					int i = 0;
					while (rs.next()) {
						vehicleModels[i] = rs.getString("vehicleModel");
						System.out.println(rs.getString("vehicleModel"));
						i++;
					}
					modelLabel.setVisible(true);
					modelList.setVisible(true);
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		});*/
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Actions Yeet");
	}



	//get Vehicle based on Make , Model , Year

	//OrderModel newOrder = new OrderModel("1,false");

	//		String password = new String(passField.getPassword());
//		
////		if (name.equals(//get user from database)) {
////				String name = nameField.getText();
////		if (password.equals(//get pass from database)) {
////			String name = nameField.getText();
////
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
