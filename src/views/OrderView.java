package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.OrderController;
import interfaces.LoginListener;
import interfaces.OrderListener;

public class OrderView extends MasterView implements ActionListener {
	

	private OrderListener OrderListener;

    public OrderView() {
    	super();   
        setSize(500,500);
       // mainFrame.setVisible(true);
                
        JLabel orders = new JLabel("Current Orders :");        
        
        JLabel placeOrder = new JLabel("Place Order");
        
    	JLabel type = new JLabel("Vehicle Type");
        
        String[] vehicleType = { "Purple Polo","Jeep" ,"Van","Car"};

	      //Create the combo box, select item at index 4.
	      //Indices start at 0, so 4 specifies the pig.
	      JComboBox jComboBox = new JComboBox(vehicleType);
		  JComboBox list = jComboBox;
	      list.setSelectedIndex(1);
	      
			setLayout(new GridBagLayout());
			
			GridBagConstraints gc = new GridBagConstraints();
			gc.anchor = GridBagConstraints.PAGE_START;
			gc.gridx = 1;
			gc.gridy = 0;
			gc.weightx = 1;
			gc.weighty = 1;
			gc.insets = new Insets(10, 0, 0, 0);
			add(new JLabel("Place Your Order"), gc);

			gc.anchor = GridBagConstraints.LINE_END;
			gc.gridx = 1;
			gc.gridy = 0;
			gc.weightx = 1;
			gc.weighty = 1;
			gc.insets = new Insets(0, 0, 0, 0);
			add(new JLabel("Vehicle Type: "), gc);

			gc.anchor = GridBagConstraints.LINE_START;
			gc.gridx = 2;
			gc.gridy = 0;
			gc.weightx = 1;
			gc.weighty = 1;
			gc.insets = new Insets(0, 0, 0, 0);
			gc.fill = GridBagConstraints.NONE;
			add(list, gc);

			gc.anchor = GridBagConstraints.LINE_END;
			gc.gridx = 1;
			gc.gridy = 1;
			gc.weightx = 1;
			gc.weighty = 1;
			gc.insets = new Insets(0, 0, 0, 0);
			gc.fill = GridBagConstraints.NONE;

			add(new JLabel("Vehicle Make: "), gc);

			
	      
    }
	@Override
	public void actionPerformed(ActionEvent e) {
	}
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
	public void setLoginListener(OrderListener orderListener) {
		this.OrderListener = orderListener;
	}
}
/*JMenuBar menuBar = new JMenuBar();

JMenu homeNav = new JMenu("Home");
homeNav.setMnemonic(KeyEvent.VK_F);
JMenu accNav = new JMenu("Account");
accNav.setMnemonic(KeyEvent.VK_F);
JMenu contactNav = new JMenu("Contact");
contactNav.setMnemonic(KeyEvent.VK_F);

JMenuItem item = new JMenuItem("Exit");
item.setMnemonic(KeyEvent.VK_E);
item.setToolTipText("Exit application");
item.addActionListener((event) -> System.exit(0));

homeNav.add(item);
menuBar.add(homeNav);
menuBar.add(accNav);
menuBar.add(contactNav);
f.setJMenuBar(menuBar);*/
 