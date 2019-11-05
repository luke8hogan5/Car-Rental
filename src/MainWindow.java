import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame{
	
    public MainWindow() {
        initWindow();
    }
	
    public void initWindow() {
        createMenuBar();

        setTitle("Car Lease and Hire");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu homeNav = new JMenu("Home");
        homeNav.setMnemonic(KeyEvent.VK_F);
        JMenu accNav = new JMenu("Account");
        accNav.setMnemonic(KeyEvent.VK_F);
        JMenu orderNav = new JMenu("Order Form");
        orderNav.setMnemonic(KeyEvent.VK_F);
        JMenu contactNav = new JMenu("Contact");
        contactNav.setMnemonic(KeyEvent.VK_F);

        JMenuItem item = new JMenuItem("Exit");
        item.setMnemonic(KeyEvent.VK_E);
        item.setToolTipText("Exit application");
        item.addActionListener((event) -> System.exit(0));

        homeNav.add(item);
        menuBar.add(homeNav);
        menuBar.add(accNav);
        menuBar.add(orderNav);
        menuBar.add(contactNav);
        

        setJMenuBar(menuBar);
    }
}
