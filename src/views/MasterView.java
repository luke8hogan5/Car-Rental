package views;

import javax.swing.JFrame;

public class MasterView extends JFrame{
	
    public MasterView() {
        initWindow();
    }
	
    public void initWindow() {

        setTitle("Scrubs Car Rental");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
    }
   
}
