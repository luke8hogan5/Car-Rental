package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomeView extends JPanel {
    MasterView parent;

    public WelcomeView(MasterView parent) {
        super();
        this.parent = parent;

        buildInterface();
    }

    private void buildInterface() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(50, 10, 0, 10);

        JButton logBtn = new JButton("Log in");
        logBtn.setBounds(0,180,80,30);
        add(logBtn,gc);

        gc.anchor = GridBagConstraints.NORTH;
        gc.gridx = 2;
        JButton regBtn = new JButton("Register");
        regBtn.setBounds(100,180,80,30);
        add(regBtn,gc);


        logBtn.addActionListener(ae -> parent.changePanel(new LoginView(parent)));

        regBtn.addActionListener(ae -> parent.changePanel(new RegisterView(parent)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int frameWidth = getTopLevelAncestor().getWidth();
        int frameHeight = getTopLevelAncestor().getHeight();

//        BufferedImage img = null;
//        try{
//            img = ImageIO.read(new File("./res/images/welcome.png"));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        g.drawImage(img.getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH), 0, 0, null);
    }
}
