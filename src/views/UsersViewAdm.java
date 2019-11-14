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

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controllers.UsersControllerAdm;
import database.Database;
import interfaces.UsersListenerAdm;
import models.UserModel;

public class UsersViewAdm extends JPanel{
	private MasterView parent;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField idField;
	private JTable userTable;
	private JScrollPane scrollPane;

	private UsersListenerAdm displayUsersListener;
			
	UsersViewAdm(MasterView parent){
		super();
		this.parent = parent;
		displayUsersListener = new UsersControllerAdm(this);
		buildInterface();

		try {getUsers();}
		catch (SQLException e) {e.printStackTrace();}
	}

	private void buildInterface() {
		JButton updateBtn = new JButton("Update");
		updateBtn.setEnabled(false);
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setEnabled(false);
		JLabel l1 = new JLabel("FirstName");
		JLabel l2 = new JLabel("Email");
		JLabel l3 = new JLabel("Id");
		JLabel l4 = new JLabel("Scrubs Car Rental");
		nameField = new JTextField(20);
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!emailField.getText().isEmpty()) {
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!(nameField.getText().isEmpty() || emailField.getText().isEmpty())) {
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
				}else{
					updateBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!(nameField.getText().isEmpty() || emailField.getText().isEmpty())) {
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
				}else{
					updateBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
				}
			}
		});
		emailField = new JTextField(20);
		emailField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!nameField.getText().isEmpty()) {
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!(nameField.getText().isEmpty() || emailField.getText().isEmpty())) {
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
				}else{
					updateBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!(nameField.getText().isEmpty() || emailField.getText().isEmpty())) {
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
				}else{
					updateBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
				}
			}
		});
		idField = new JTextField(20);
		idField.setEditable(false);
		userTable = new JTable();
		scrollPane = new JScrollPane();

		GroupLayout layout = new GroupLayout(parent.getContentPane());
		parent.getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
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
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(idField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
												.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 163,GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
									.addComponent(updateBtn,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGap(27, 27, 27)
									.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 75,GroupLayout.PREFERRED_SIZE)))
							.addGap(18, 18, 18)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 800,GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(layout.createSequentialGroup()
							.addGap(218, 218, 218)
							.addComponent(l4)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(l4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
										.addComponent(l1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
									.addGap(30, 30, 30)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
										.addComponent(l2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
									.addGap(30, 30, 30)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(idField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
										.addComponent(l3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
									.addGap(39, 39, 39)
									.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
											.addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))))
								.addComponent(scrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(29, Short.MAX_VALUE)));

		parent.pack();
		parent.setLocationRelativeTo(null);

		updateBtn.addActionListener(event -> {
			try {
				fireUpdateUserEvent(nameField.getText(),
									emailField.getText(),
									Integer.parseInt(idField.getText()));
				getUsers();
				idField.setText("");
				nameField.setText("");
				emailField.setText("");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		deleteBtn.addActionListener(event -> {
			try {
				fireDeleteUserEvent(Integer.parseInt(idField.getText()));
				getUsers();
				idField.setText("");
				nameField.setText("");
				emailField.setText("");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	//set the values of a row to the textfields
	private void userTableMouseClicked(MouseEvent event) {
		int i = userTable.getSelectedRow();
		TableModel model = userTable.getModel();
		nameField.setText(model.getValueAt(i, 1).toString());
		emailField.setText(model.getValueAt(i, 2).toString());
		idField.setText(model.getValueAt(i, 0).toString());
	}

	private void getUsers() throws SQLException {
		setTable(displayUsersListener.getUsers());
	}
	private void fireUpdateUserEvent(String name, String email, int id) throws SQLException {
		displayUsersListener.updatePerformed(name, email, id);
	}
	private void fireDeleteUserEvent(int id) throws SQLException {
		displayUsersListener.deletePerformed(id);
	}

	private void setTable(Vector<Vector<Object>> data) {

		String[] titles= {"ID", "Name", "Email", "Loyalty Pts", "Balance"};


		DefaultTableModel tableModel = new DefaultTableModel(titles, 0);
		userTable = new JTable(tableModel);

		for (Vector<Object> datum : data) tableModel.addRow(datum);

		userTable.setPreferredScrollableViewportSize(userTable.getPreferredSize());
		userTable.setShowHorizontalLines(true);
		userTable.setShowVerticalLines(true);

		userTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				userTableMouseClicked(event);
			}
		});

		scrollPane.getViewport().add(userTable);

	}
}
