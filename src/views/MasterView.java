package views;

import models.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MasterView extends JFrame{
    private int id;

    protected JMenuBar menuBar;
	
    public MasterView() {
        initWindow();
    }
	
    public void initWindow() {

        setTitle("Scrubs Car Rental");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		if(id != 0) {
//            menuBar = new JMenuBar();
//            JMenu homeNav = new JMenu("Home");
//            homeNav.setMnemonic(KeyEvent.VK_F);
//            JMenu accNav = new JMenu("Account");
//            accNav.setMnemonic(KeyEvent.VK_F);
//            JMenu orderNav = new JMenu("Order Form");
//            orderNav.setMnemonic(KeyEvent.VK_F);
//            JMenu contactNav = new JMenu("Contact");
//            contactNav.setMnemonic(KeyEvent.VK_F);

//            menuBar.add(homeNav);
//            menuBar.add(accNav);
//            menuBar.add(orderNav);
//            menuBar.add(contactNav);
        }



//        setJMenuBar(menuBar);
    }

    private void updateSearchBar(){

    }
   
}
