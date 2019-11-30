package views;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import controllers.LoginController;
import interfaces.LoginListener;

public class LoginView extends JPanel implements ActionListener {

	private JTextField nameField;
	private JPasswordField passField;
	private MasterView parent;

	private LoginListener loginListener;

		
	LoginView(MasterView parent) {
		super();
		this.parent = parent;
		loginListener = new LoginController();

		buildInterface();
	}

	private void buildInterface() {
		nameField = new JTextField(10);
		passField = new JPasswordField(10);
		JButton okButton = new JButton("Login");
		JButton backbtn = new JButton("Back");

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

        backbtn.addActionListener(ae -> parent.changePanel(new WelcomeView(parent)));
		
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(100, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;

		add(new JLabel("Name: "), gc);

		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(100, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;

		add(nameField, gc);

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;

		add(new JLabel("Password: "), gc);

		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;

		add(passField, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 4;
		gc.weightx = 1;
		gc.weighty = 100;
		gc.fill = GridBagConstraints.NONE;

		add(okButton, gc);

		okButton.addActionListener(this);
		parent.getRootPane().setDefaultButton(okButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String password = new String(passField.getPassword());

		try {
			fireLoginEvent(name, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void fireLoginEvent(String name, String pass) throws SQLException {
		if (loginListener != null) {
			loginListener.loginPerformed(name, pass,parent);

			if(parent.getCurrentUser() == null) {
				JOptionPane.showMessageDialog(this, "Incorrect username or password", "Login Error", JOptionPane.WARNING_MESSAGE);
			}else if(parent.getCurrentUser().getUserType() == 1) {
				parent.changePanel(new CatalogView(parent));
			}else if(parent.getCurrentUser().getUserType() == 2) {
				parent.changePanel(new OrderViewAdm(parent));
			}
		}
	}

}
