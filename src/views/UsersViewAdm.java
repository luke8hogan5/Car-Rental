package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import database.Database;
import interfaces.UsersListenerAdm;
import models.UserModel;

public class UsersViewAdm extends MasterView {

	private JButton saveBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField idField;
	private JTextField addressField;
	private JTextField loyaltyField;
	private JTextField balanceField;
	private JTable userTable;
	private JScrollPane scrollPane;
	private JLabel l1,l2,l3,l4;

	private UsersListenerAdm displayUsersListener;
			
	public UsersViewAdm() throws SQLException {
		super();
			
	    	l1 = new JLabel("FirstName");
	    	l2 = new JLabel("Email");
	    	l3 = new JLabel("Id");
	    	l4 = new JLabel("Scrubs Car Rental");
			nameField = new JTextField(20);
			emailField = new JTextField(20);
			idField = new JTextField(20);
			addressField = new JTextField(20);
			loyaltyField = new JTextField(20);
			balanceField = new JTextField(20);
			saveBtn = new JButton("Add");
			updateBtn = new JButton("Update");
			deleteBtn = new JButton("Delete");
			userTable = new JTable();
			scrollPane = new JScrollPane();
			
	        //setResizable(false);

	    	Connection conn = Database.getConnection();
	        Statement st = conn.createStatement();
	        ResultSet rs;
	        rs = st.executeQuery("select user_id, userName, email, loyaltyRating, balanceDue from account where userType = 1;");
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
	        userTable.setModel(tableModel);
	        userTable.setCellSelectionEnabled(true);
	        scrollPane.setViewportView(userTable);
	        
	        userTable.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent event) {
	                userTableMouseClicked(event);
	            }
	        });
	        
	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(l1,GroupLayout.PREFERRED_SIZE, 80,GroupLayout.PREFERRED_SIZE)
	                                .addGap(18, 18, 18)
	                                .addComponent(nameField,GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                                    .addComponent(l2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(l3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                                    .addComponent(idField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(saveBtn,GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
	                        .addGap(27, 27, 27)
	                        .addComponent(updateBtn,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
	                        .addGap(27, 27, 27)
	                        .addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 75,GroupLayout.PREFERRED_SIZE)))
                    		.addGap(18, 18, 18)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 800,GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addGap(218, 218, 218)
	                .addComponent(l4)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(30, 30, 30)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(30, 30, 30)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(39, 39, 39)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(29, Short.MAX_VALUE))
	        );

	        pack();
	        setLocationRelativeTo(null);

	        saveBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {					
	                try {
						saveBtnActionPerformed(event);
		            	
						String name = nameField.getText();
						String email = emailField.getText();
						int id = Integer.parseInt(idField.getText());

						fireAddUserEvent(new UserModel(name, email, id));

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
						
						String name = nameField.getText();
						String email = emailField.getText();
						int id = Integer.parseInt(idField.getText());
						
						fireUpdateUserEvent(new UserModel(name, email, id));
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
						
						int id = Integer.parseInt(idField.getText());
						
						fireDeleteUserEvent(new UserModel(id));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });

			}
	
		public void delete(int id) throws SQLException {
	
			Connection con = Database.getConnection();
			String sql = "DELETE FROM `account` WHERE user_id='" + id + "'";
			Statement st = con.createStatement();
			st.execute(sql);
		}

		public void saveUser(String fname, String lname, int id) throws SQLException {
			Connection con = Database.getConnection();
			String sql = "INSERT INTO `account`(`userName`, `email`, `user_id`) "
					+ "VALUES ('" + fname + "','" + lname + "','" + id + "')";
			Statement st = con.createStatement();
			st.execute(sql);
		}
		
		public void update(String fname, String lname, int id) throws SQLException {
    
			Connection con = Database.getConnection();
			String sql = "UPDATE `account`SET userName='" + fname + "',email='" + lname + "'WHERE user_id='" + id + "'";
			Statement st = con.createStatement();
			st.execute(sql);
		}

	//handles delete button action
	private void deleteBtnActionPerformed(ActionEvent event) throws SQLException {
		// TODO add your handling code here:
		int i = userTable.getSelectedRow();
		if (i >= 0) {

			if (userTable.getSelectedRows().length == 1) {
            	DefaultTableModel model = (DefaultTableModel) userTable.getModel();
                int id =  Integer.parseInt(model.getValueAt(i, 0).toString());
                delete(id);

                	//TO BE CHANGED
    	    	Connection conn = Database.getConnection();
    	        Statement st = conn.createStatement();
    	        ResultSet rs;
    	        rs = st.executeQuery("select user_id, userName, email, loyaltyRating, balanceDue from account where userType = 1;");
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
    	        userTable.setModel(tableModel);
    	        userTable.setCellSelectionEnabled(true);
    	        scrollPane.setViewportView(userTable);
            }
		}
	}

	private void updateBtnActionPerformed(ActionEvent evt) throws SQLException {
		// TODO add your handling code here:
		String fname = nameField.getText().trim();
		String lname = emailField.getText().trim();
		int id = Integer.parseInt(idField.getText().trim());
    
		if (!fname.isEmpty() && !lname.isEmpty()) {
        		update(fname, lname, id);
        		
        		//TO BE CHANGED
        		
    	    	Connection conn = Database.getConnection();
    	        Statement st = conn.createStatement();
    	        ResultSet rs;
    	        rs = st.executeQuery("select user_id, userName, email, loyaltyRating, balanceDue from account where userType = 1;");
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
    	        userTable.setModel(tableModel);
    	        userTable.setCellSelectionEnabled(true);
    	        scrollPane.setViewportView(userTable);
        }
	}

	private void saveBtnActionPerformed(ActionEvent event) throws SQLException {

		String fname = nameField.getText();
		String lname = emailField.getText();
		int id = Integer.parseInt(idField.getText());
    
		if (!fname.isEmpty() && !lname.isEmpty()) {

                saveUser(fname, lname, id);
                DefaultTableModel model = (DefaultTableModel) userTable.getModel();
                Object[] row = new Object[5];
                row[1] = fname;
                row[2] = lname;
                row[0] = id;
                row[3] = 0;
                row[4] = 0;
                model.addRow(row);
        } 
	}
	//set the values of a row to the textfields
	private void userTableMouseClicked(MouseEvent event) {
		// TODO add your handling code here:
		int i = userTable.getSelectedRow();
		TableModel model = userTable.getModel();
		nameField.setText(model.getValueAt(i, 1).toString());
		emailField.setText(model.getValueAt(i, 2).toString());
		idField.setText(model.getValueAt(i, 0).toString());
}




			public void setUsersAdmListener(UsersListenerAdm displayUsersListener) {
				this.displayUsersListener = displayUsersListener;
			}

			public void fireAddUserEvent(UserModel event) throws SQLException {
				if (displayUsersListener != null) {
					displayUsersListener.addPerformed(event);
				}
			}
			public void fireUpdateUserEvent(UserModel event) throws SQLException {
				if (displayUsersListener != null) {
					displayUsersListener.updatePerformed(event);
				}
			}
			public void fireDeleteUserEvent(UserModel event) throws SQLException {
				if (displayUsersListener != null) {
					displayUsersListener.deletePerformed(event);
				}
			}

}
