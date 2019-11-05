package views;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.LoginController;
import interfaces.LoginListener;
import models.UserModel;

public class LoginView extends MasterView implements ActionListener {
	
//	public void initWindow() {	
//		LoginView logView = new LoginView();
//		LoginController logCtrl = new LoginController(logView);
//			
//		logView.setLoginListener(logCtrl);
//	}
	
	private JButton okButton;
	private JTextField nameField;
	private JPasswordField passField;

	private LoginListener loginListener;
		
	public LoginView() {
		super();
		
		nameField = new JTextField(10);
		passField = new JPasswordField(10);
		okButton = new JButton("Login");

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
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
			
//			addWindowListener(new WindowAdapter() {
//				
//				@Override
//				public void windowOpened(WindowEvent e) {
//					try {
//						Database.getInstance().connect();
//					} catch (Exception e1) {
//						JOptionPane.showMessageDialog(View.this, "Unable to connect to database.",
//								"Error", JOptionPane.WARNING_MESSAGE);
//						e1.printStackTrace();
//					}
//				}
	//
//				@Override
//				public void windowClosing(WindowEvent e) {
//					Database.getInstance().disconnect();
//				}
//				
//			});
		}

		@Override
		public void actionPerformed(ActionEvent e) {
		}
//			String password = new String(passField.getPassword());
//			
////			if (name.equals(//get user from database)) {
////					String name = nameField.getText();
////			if (password.equals(//get pass from database)) {
////				String name = nameField.getText();
////
////				fireLoginEvent(new RegisterModel(name, password));
////			} else {
////				JOptionPane.showMessageDialog(this, "User not found.",
////						"Error", JOptionPane.WARNING_MESSAGE);
////			}
////		}
//
		public void setLoginListener(LoginListener loginListener) {
			this.loginListener = loginListener;
		}

		public void fireLoginEvent(UserModel event) {
			if (loginListener != null) {
				loginListener.loginPerformed(event);
			}
		}

//	}

}
