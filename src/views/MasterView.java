package views;

import models.UserModel;
import javax.swing.*;

public class MasterView extends JFrame{
    private UserModel currentUser;
    private JPanel currentPanel;

	
    public MasterView() {
        initWindow();
    }
	
    private void initWindow() {

        setTitle("Scrubs Car Rental");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

        changePanel(new WelcomeView(this));
    }

    void changePanel(JPanel panel){
        if(currentPanel != panel){
            if(currentPanel != null)
                remove(currentPanel);
            currentPanel = panel;
            add(panel);

            revalidate();
        }
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }
}
