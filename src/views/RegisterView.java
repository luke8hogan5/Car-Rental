package views;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.RegisterController;
import interfaces.RegisterListener;
import models.UserModel;

public class RegisterView extends JFrame implements ActionListener {

	private JButton okButton;
	private JTextField nameField;
	private JPasswordField passField;
	private JPasswordField repeatPassField;

	private RegisterListener registerListener;

	public static void regGUI() {
		RegisterView view = new RegisterView();
		RegisterController controller = new RegisterController(view);
		
		view.setRegisterListener(controller);
	}
	
	public RegisterView() {
		super();

		nameField = new JTextField(10);
		passField = new JPasswordField(10);
		repeatPassField = new JPasswordField(10);
		okButton = new JButton("Create user");

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

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;

		add(new JLabel("Repeat password: "), gc);

		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;

		add(repeatPassField, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 4;
		gc.weightx = 1;
		gc.weighty = 100;
		gc.fill = GridBagConstraints.NONE;

		add(okButton, gc);

		okButton.addActionListener(this);
		
//		addWindowListener(new WindowAdapter() {
//			
//			@Override
//			public void windowOpened(WindowEvent e) {

//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				Database.getInstance().disconnect();
//			}
//			
//		});

		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String password = new String(passField.getPassword());
		String repeat = new String(repeatPassField.getPassword());

		if (password.equals(repeat)) {
			String name = nameField.getText();

			try {
				fireRegisterEvent(new UserModel(name, password));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Passwords do not match.",
					"Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void setRegisterListener(RegisterListener registerListener) {
		this.registerListener = registerListener;
	}

	public void fireRegisterEvent(UserModel event) throws SQLException {
		if (registerListener != null) {
			registerListener.registerPerformed(event);
		}
	}

}
