package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import database.Database;
import interfaces.UsersListenerAdm;
import interfaces.VehicleListenerAdm;
import models.UserModel;
import models.VehicleModelAdm;

public class VehicleViewAdm extends MasterView {

	private JButton saveBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JTextField vehicleMakeField;
	private JTextField vehicleModelField;
	private JTextField vehicleIdField;
	private JTextField vehicleYearField;
	private JTextField vehiclePriceField;
	private JTextField vehicleAvailable;
	private JTable vehicleTable;
	private JScrollPane scrollPane;
	private JLabel l1,l2,l3,l4,l5,l6,l7;

	private VehicleListenerAdm vehicleListener;
			
	public VehicleViewAdm() throws SQLException {
		super();
			
	    	l1 = new JLabel("Vehicle Make");
	    	l2 = new JLabel("Vehile Model");
	    	l3 = new JLabel("VehicleId");
	    	l6 = new JLabel("Vehicle Price");
	    	l5 = new JLabel("Vehicle Year");
	    	l7 = new JLabel("Availability");
	    	l4 = new JLabel("Scrubs Car Rental");
			vehicleMakeField = new JTextField(10);
			vehicleModelField = new JTextField(10);
			vehicleIdField = new JTextField(10);
			vehiclePriceField = new JTextField(10);
			vehicleYearField = new JTextField(10);
			vehicleAvailable = new JTextField(10);
			saveBtn = new JButton("Add");
			updateBtn = new JButton("Update");
			deleteBtn = new JButton("Delete");
			vehicleTable = new JTable();
			scrollPane = new JScrollPane();
			
	        setResizable(false);
	        
		    	Connection conn = Database.getConnection();
		        Statement stat = conn.createStatement();
		        ResultSet rs;
		        rs = stat.executeQuery("select vehicleMake,vehicleModel, vehicle_id, vehicleYear, vehiclePrice, isAvailable from vehicle;");
		        ResultSetMetaData md = rs.getMetaData();
		        int columnCount = md.getColumnCount();
		        Vector columns = new Vector(columnCount);
		 
		      //store column names
		        for(int i=1; i<=columnCount; i++)
		          columns.add(md.getColumnName(i));
		         
		        Vector data = new Vector();
		        Vector row;
		       
		        while (rs.next()) {
		          
		          row = new Vector(columnCount);
		             for(int i=1; i<=columnCount; i++)
		             {
		                 row.add(rs.getString(i));
		             }
		             data.add(row);            
		        }
		         
		        //Display in JTable  
		        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
		        vehicleTable.setModel(tableModel);
		        vehicleTable.setCellSelectionEnabled(true);
	        

	        vehicleTable.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent event) {
	                userTableMouseClicked(event);
	            }
	        });
	        scrollPane.setViewportView(vehicleTable);
	        
	        
	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                            .addComponent(l1,GroupLayout.PREFERRED_SIZE, 90,GroupLayout.PREFERRED_SIZE)
	                            .addComponent(vehicleMakeField,GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
	        	                	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        	                		.addGroup(layout.createSequentialGroup()
	                                    .addComponent(l2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(vehicleModelField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))
	        	                        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        		                            .addGroup(layout.createSequentialGroup()
	        		                            .addComponent(l3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
	        		                            .addComponent(vehicleIdField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))
	        			                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        			                        	.addGroup(layout.createSequentialGroup()
	        			                        			.addComponent(l5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
	        			                        			.addComponent(vehicleYearField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
	        				                        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        				                        			.addGroup(layout.createSequentialGroup()
	        				                        			.addComponent(l6, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
	        				                        			.addComponent(vehiclePriceField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))
	        				                        				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			        				                        			.addGroup(layout.createSequentialGroup()
			        				                        			.addComponent(l7, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
			        				                        			.addComponent(vehicleAvailable, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))
	        				))))))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(saveBtn,GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
	                        .addGap(35, 35, 35)
	                        .addComponent(updateBtn,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
	                        .addGap(35, 35, 35)
	                        .addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 75,GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 600,GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addGap(300, 300, 300)
	                .addComponent(l4)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(l4).addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(vehicleMakeField)
	                            .addComponent(l1)).addGap(30, 30, 30).
	                        addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                            .addComponent(vehicleModelField).addComponent(l2)).addGap(30, 30, 30)
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(vehicleIdField)
	                            .addComponent(l3)).addGap(39, 39, 39)
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                            .addComponent(vehicleYearField).addComponent(l5)).addGap(30, 30, 30)
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                            .addComponent(vehiclePriceField).addComponent(l6)).addGap(30, 30, 30)
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                            .addComponent(vehicleAvailable).addComponent(l7)).addGap(30, 30, 30)
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                                .addComponent(deleteBtn).addComponent(updateBtn)).addComponent(saveBtn)))
	                    .addComponent(scrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 600,GroupLayout.PREFERRED_SIZE)).addContainerGap(30, Short.MAX_VALUE))
	        );
	        pack();
	        setLocationRelativeTo(null);

	        saveBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {					
	                try {
						saveBtnActionPerformed(event);
		            	
						String vehicleMake = vehicleMakeField.getText();
						String vehicleModel = vehicleModelField.getText();
						int vehicleId = Integer.parseInt(vehicleIdField.getText());
						int vehicleYear = Integer.parseInt(vehicleYearField.getText());
						double vehiclePrice = Double.parseDouble(vehiclePriceField.getText());
						int available = Integer.parseInt(vehicleAvailable.getText());
						

						fireAddVehicleEvent(new VehicleModelAdm(vehicleId, vehicleModel, vehicleMake, vehicleYear,
								vehiclePrice, available));

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
	        
	        updateBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	                try {
						updateBtnActionPerformed(event);
						
						String vehicleMake = vehicleMakeField.getText();
						String vehicleModel = vehicleModelField.getText();
						int vehicleId = Integer.parseInt(vehicleIdField.getText());
						int vehicleYear = Integer.parseInt(vehicleYearField.getText());
						double vehiclePrice = Double.parseDouble(vehiclePriceField.getText());
						int available = Integer.parseInt(vehicleAvailable.getText());
						
						fireUpdateVehicleEvent(new VehicleModelAdm(vehicleId, vehicleModel, vehicleMake, vehicleYear,
								vehiclePrice, available));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });

	        deleteBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	               	try {
						deleteBtnActionPerformed(event);
						
						int id = Integer.parseInt(vehicleIdField.getText());
						
						fireDeleteVehicleEvent(new VehicleModelAdm(id));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });

			}
	
		public void delete(int vehicleId) throws SQLException {
	
			Connection con = Database.getConnection();
			String sql = "DELETE FROM `vehicle` WHERE vehicle_id='" + vehicleId + "'";
			Statement st = con.createStatement();
			st.execute(sql);
		}

		public void saveUser(String vehicleMake, String vehicleModel, int vehicleId, int vehicleYear, double vehiclePrice, int available) throws SQLException {
			Connection con = Database.getConnection();
			String sql = "INSERT INTO vehicle(vehicleMake, vehicleModel, vehicle_id, vehicleYear, vehiclePrice, isAvailable) VALUES (?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vehicleMake);
		    ps.setString(2, vehicleModel);
		    ps.setInt(3, vehicleId);
			ps.setInt(4, vehicleYear);
		    ps.setDouble(5, vehiclePrice);
		    ps.setInt(6, available);
		    ps.executeUpdate(); 
		}
		
		public void update(String vehicleMake, String vehicleModel, int vehicleId, int vehicleYear, double vehiclePrice, int available) throws SQLException {
    
			Connection con = Database.getConnection();
			String sql = "UPDATE `vehicle`SET vehicleMake='" + vehicleMake + "',vehicleModel='" + vehicleModel + "',vehicleYear='" + vehicleYear + "',vehiclePrice='" + vehiclePrice + "',isAvailable='" + available + "'WHERE vehicle_id='" + vehicleId + "'";
			Statement st = con.createStatement();
			st.execute(sql);
		}

	//handles delete button action
	private void deleteBtnActionPerformed(ActionEvent event) throws SQLException {
		// TODO add your handling code here:
		int i = vehicleTable.getSelectedRow();
		if (i >= 0) {

			if (vehicleTable.getSelectedRows().length == 1) {
            	DefaultTableModel model = (DefaultTableModel) vehicleTable.getModel();
                int id =  Integer.parseInt(model.getValueAt(i, 2).toString());
                delete(id);

                // to be changed
        		Connection conn = Database.getConnection();
		        Statement stat = conn.createStatement();
		        ResultSet rs;
		        rs = stat.executeQuery("select vehicleMake,vehicleModel, vehicle_id, vehicleYear, vehiclePrice, isAvailable from vehicle;");
		        ResultSetMetaData md = rs.getMetaData();
		        int columnCount = md.getColumnCount();
		        Vector columns = new Vector(columnCount);
		 
		      //store column names
		        for(int j=1; j<=columnCount; j++)
		          columns.add(md.getColumnName(j));
		         
		        Vector data = new Vector();
		        Vector row;
		       
		        while (rs.next()) {
		          
		          row = new Vector(columnCount);
		             for(int j=1; j<=columnCount; j++)
		             {
		                 row.add(rs.getString(j));
		             }
		             data.add(row);            
		        }
		         
		        //Display in JTable  
		        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
		        vehicleTable.setModel(tableModel);
		        vehicleTable.setCellSelectionEnabled(true);
		        
		        scrollPane.setViewportView(vehicleTable);
            }
		}
	}

	private void updateBtnActionPerformed(ActionEvent evt) throws SQLException {
		// TODO add your handling code here:
		String vehicleMake = vehicleMakeField.getText();
		String vehicleModel = vehicleModelField.getText();
		int vehicleId = Integer.parseInt(vehicleIdField.getText());
		int vehicleYear = Integer.parseInt(vehicleYearField.getText());
		double vehiclePrice = Double.parseDouble(vehiclePriceField.getText());
		int available = Integer.parseInt(vehicleAvailable.getText());
    
		if (!vehicleMake.isEmpty() && !vehicleModel.isEmpty()) {
        		update(vehicleMake, vehicleModel, vehicleId, vehicleYear, vehiclePrice, available);
        		
        		// to be changed
        		Connection conn = Database.getConnection();
		        Statement stat = conn.createStatement();
		        ResultSet rs;
		        rs = stat.executeQuery("select vehicleMake,vehicleModel, vehicle_id, vehicleYear, vehiclePrice, isAvailable from vehicle;");
		        ResultSetMetaData md = rs.getMetaData();
		        int columnCount = md.getColumnCount();
		        Vector columns = new Vector(columnCount);
		 
		      //store column names
		        for(int i=1; i<=columnCount; i++)
		          columns.add(md.getColumnName(i));
		         
		        Vector data = new Vector();
		        Vector row;
		       
		        while (rs.next()) {
		          
		          row = new Vector(columnCount);
		             for(int i=1; i<=columnCount; i++)
		             {
		                 row.add(rs.getString(i));
		             }
		             data.add(row);            
		        }
		         
		        //Display in JTable  
		        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
		        vehicleTable.setModel(tableModel);
		        vehicleTable.setCellSelectionEnabled(true);
		        
		        scrollPane.setViewportView(vehicleTable);
	        

        }
	}

	private void saveBtnActionPerformed(ActionEvent event) throws SQLException {

		String vehicleMake = vehicleMakeField.getText();
		String vehicleModel = vehicleModelField.getText();
		int vehicleId = Integer.parseInt(vehicleIdField.getText());
		int vehicleYear = Integer.parseInt(vehicleYearField.getText());
		double vehiclePrice = Double.parseDouble(vehiclePriceField.getText());
		int available = Integer.parseInt(vehicleAvailable.getText());
    
		if (!vehicleMake.isEmpty() && !vehicleModel.isEmpty()) {

                saveUser(vehicleMake, vehicleModel, vehicleId, vehicleYear, vehiclePrice, available);
                DefaultTableModel model = (DefaultTableModel) vehicleTable.getModel();
                Object[] row = new Object[6];
                row[0] = vehicleMake;
                row[1] = vehicleModel;
                row[2] = vehicleId;
                row[3] = vehicleYear;
                row[4] = vehiclePrice;
                row[5] = available;
                model.addRow(row);
        } 
	}
	//set the values of a row to the textfields
	private void userTableMouseClicked(MouseEvent event) {
		// TODO add your handling code here:
		int i = vehicleTable.getSelectedRow();
		TableModel model = vehicleTable.getModel();
		vehicleMakeField.setText(model.getValueAt(i, 0).toString());
		vehicleModelField.setText(model.getValueAt(i, 1).toString());
		vehicleIdField.setText(model.getValueAt(i, 2).toString());
		vehicleYearField.setText(model.getValueAt(i, 3).toString());
		vehiclePriceField.setText(model.getValueAt(i, 4).toString());
		vehicleAvailable.setText(model.getValueAt(i, 5).toString());
	}

			public void setVehicleListener(VehicleListenerAdm vehicleListener) {
				this.vehicleListener = vehicleListener;
			}

			public void fireAddVehicleEvent(VehicleModelAdm event) throws SQLException {
				if (vehicleListener != null) {
					vehicleListener.addPerformed(event);
				}
			}
			public void fireUpdateVehicleEvent(VehicleModelAdm event) throws SQLException {
				if (vehicleListener != null) {
					vehicleListener.updatePerformed(event);
				}
			}
			public void fireDeleteVehicleEvent(VehicleModelAdm event) throws SQLException {
				if (vehicleListener != null) {
					vehicleListener.deletePerformed(event);
				}
			}

}
