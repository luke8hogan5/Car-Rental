package views;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controllers.VehicleControllerAdm;
import interfaces.VehicleListenerAdm;

public class VehicleViewAdm extends JPanel {
	private MasterView parent;
	private JTextField makeField;
	private JTextField modelField;
	private JTextField idField;
	private JTextField yearField;
	private JTextField priceField;
	private JTextField availableField;
	private JTextField typeField;
	private JTable vehicleTable;
	private JScrollPane scrollPane;

	private VehicleListenerAdm vehicleListener;
			
	VehicleViewAdm(MasterView parent){
		super();
		this.parent = parent;
		vehicleListener = new VehicleControllerAdm(this);

		buildInterface();
		getVehicles();
	}

	private void buildInterface() {
		JLabel l1 = new JLabel("Vehicle ID");
		JLabel l8 = new JLabel("Vehicle Type");
		JLabel l2 = new JLabel("Vehile Make");
		JLabel l3 = new JLabel("Vehicle Model");
		JLabel l6 = new JLabel("Vehicle Price");
		JLabel l5 = new JLabel("Vehicle Year");
		JLabel l7 = new JLabel("Availability");
		JLabel l4 = new JLabel("Scrubs Car Rental");
		makeField = new JTextField(10);
		modelField = new JTextField(10);
		idField = new JTextField(10);
		idField.setEditable(false);
		priceField = new JTextField(10);
		yearField = new JTextField(10);
		availableField = new JTextField(10);
		typeField = new JTextField(10);
		JButton saveBtn = new JButton("Add");
		JButton updateBtn = new JButton("Update");
		JButton deleteBtn = new JButton("Delete");
		vehicleTable = new JTable();
		scrollPane = new JScrollPane();

		parent.setResizable(false);

		GroupLayout layout = new GroupLayout(parent.getContentPane());
		parent.getContentPane().setLayout(layout);

		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);

		JButton backbtn = new JButton("Back");
		backbtn.setBounds(0,180,80,30);
		add(backbtn,gc);
		backbtn.addActionListener(ae -> parent.changePanel(new OrderViewAdm(parent)));

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										/** Setup Textfields and Labels */
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
											.addComponent(l1,GroupLayout.PREFERRED_SIZE, 90,GroupLayout.PREFERRED_SIZE)
											.addComponent(idField,GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))

									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
											.addComponent(l8,GroupLayout.PREFERRED_SIZE, 90,GroupLayout.PREFERRED_SIZE)
											.addComponent(typeField,GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))

									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
											.addComponent(l2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
											.addComponent(makeField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))

									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
											.addComponent(l3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
											.addComponent(modelField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))

									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
											.addComponent(l5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
											.addComponent(yearField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))

									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
											.addComponent(l6, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
											.addComponent(priceField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))

									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
											.addComponent(l7, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
											.addComponent(availableField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE)))))))))

									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
									.addComponent(saveBtn,GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(35, 35, 35)
									.addComponent(updateBtn,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGap(35, 35, 35)
									.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 75,GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 600,GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(layout.createSequentialGroup()
							.addGap(300, 300, 300)
							.addComponent(l4)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(l4).addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(idField)
									.addComponent(l1))
								.addGap(30, 30, 30)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(typeField)
									.addComponent(l8))
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(makeField)
									.addComponent(l2))
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(modelField)
									.addComponent(l3))
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(yearField)
									.addComponent(l5))
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(priceField)
									.addComponent(l6))
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(availableField)
									.addComponent(l7))
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(deleteBtn)
										.addComponent(updateBtn))
									.addComponent(saveBtn)))
							.addComponent(scrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 600,GroupLayout.PREFERRED_SIZE))
						.addContainerGap(30, Short.MAX_VALUE)));

		parent.pack();
		parent.setLocationRelativeTo(null);

		saveBtn.addActionListener(event -> {
			try {
				if(!(makeField.getText().isEmpty() || modelField.getText().isEmpty() || yearField.getText().isEmpty()
						|| priceField.getText().isEmpty() || availableField.getText().isEmpty() || typeField.getText().isEmpty())) {
					String vehicleMake = makeField.getText();
					String vehicleModel = modelField.getText();
					String type  = typeField.getText();
					int vehicleYear = Integer.parseInt(yearField.getText());
					double vehiclePrice = Double.parseDouble(priceField.getText());


					fireAddVehicleEvent(vehicleMake, vehicleModel,type, vehicleYear, vehiclePrice);
				}else{
					JOptionPane.showMessageDialog(null, "Error, Please ensure no fields are left empty",
													"Error", JOptionPane.WARNING_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		updateBtn.addActionListener(event -> {
			try {
				if(!(makeField.getText().isEmpty() || modelField.getText().isEmpty() || idField.getText().isEmpty()|| yearField.getText().isEmpty()
						|| priceField.getText().isEmpty() || availableField.getText().isEmpty() || typeField.getText().isEmpty())){
					String make = makeField.getText();
					String model = modelField.getText();
					String type = typeField.getText();
					int id = Integer.parseInt(idField.getText());
					int year = Integer.parseInt(yearField.getText());
					double price = Double.parseDouble(priceField.getText());
					int available = Integer.parseInt(availableField.getText());

					fireUpdateVehicleEvent(id, make, model, year, price, available, type);

					getVehicles();
				}else{
					JOptionPane.showMessageDialog(null, "Error, Please ensure no fields are left empty",
							"Error", JOptionPane.WARNING_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		deleteBtn.addActionListener(event -> {
			try {
				if(!idField.getText().isEmpty()) {
					int id = Integer.parseInt(idField.getText());
					fireDeleteVehicleEvent(id);

					getVehicles();
				}else
					JOptionPane.showMessageDialog(null, "Please input ID or select option from table",
													"Selection invalid", JOptionPane.WARNING_MESSAGE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	//set the values of a row to the textfields
	private void vehicleTableMouseClicked(MouseEvent event) {
		// TODO add your handling code here:
		int i = vehicleTable.getSelectedRow();
		TableModel model = vehicleTable.getModel();
		idField.setText(model.getValueAt(i, 0).toString());
		typeField.setText(model.getValueAt(i, 1).toString());
		makeField.setText(model.getValueAt(i, 2).toString());
		modelField.setText(model.getValueAt(i, 3).toString());
		yearField.setText(model.getValueAt(i, 4).toString());
		priceField.setText(model.getValueAt(i, 5).toString());
		availableField.setText(model.getValueAt(i, 6).toString());
	}

	private void getVehicles(){
		try {
			setTable(vehicleListener.getVehicles());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void fireAddVehicleEvent(String make, String model, String type, int year, double price) throws SQLException {
			vehicleListener.addPerformed(make, model, year, price, type);
	}
	private void fireUpdateVehicleEvent(int id, String make, String model, int year, double price, int available, String type) throws SQLException {
			vehicleListener.updatePerformed(id, make, model, year, price, available, type);
	}
	private void fireDeleteVehicleEvent(int id) throws SQLException {
		vehicleListener.deletePerformed(id);
	}

	private void setTable(Vector<Vector<Object>> data) {

		String[] titles= {"ID", "Type", "Make", "Model", "Year", "Price", "Available"};


		DefaultTableModel tableModel = new DefaultTableModel(titles, 0);
		vehicleTable = new JTable(tableModel);

		for (Vector<Object> datum : data){
			tableModel.addRow(datum);
			for(int i=0; i<datum.size(); i++)
				vehicleTable.isCellEditable(data.indexOf(datum), i);
		}

		vehicleTable.setPreferredScrollableViewportSize(vehicleTable.getPreferredSize());
		vehicleTable.setShowHorizontalLines(true);
		vehicleTable.setShowVerticalLines(true);

		vehicleTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				vehicleTableMouseClicked(event);
			}
		});

		scrollPane.getViewport().add(vehicleTable);

		revalidate();

	}
}
