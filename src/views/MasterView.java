package views;

import models.UserModel;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//            List<StackTraceElement> stack = Arrays.asList(Thread.currentThread().getStackTrace());
//            for(StackTraceElement e : stack)    System.out.println(e);
            if(currentPanel != null)
                remove(currentPanel); // Remove previous panel
            currentPanel = panel;
            add(panel);     // Transition to new panel

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
