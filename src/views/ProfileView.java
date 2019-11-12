package views;

import interfaces.ProfileListener;
import models.UserModel;

import javax.swing.*;

import controllers.ProfileController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProfileView extends JPanel {

    private MasterView parent;
    private JTextField name;
    private JTextArea address;
    private JTextField email;
    private JButton updateBtn,backbtn;

    private ProfileListener listener;

    public ProfileView(MasterView parent) {
        super();
        this.parent = parent;
        listener = new ProfileController(this);

        name = new JTextField(32);
        name.setText(parent.getCurrentUser().getName());
        address = new JTextArea(parent.getCurrentUser().getAddress());
        address.setMaximumSize(new Dimension(64, 30));
        address.setLineWrap(true);
        address.setWrapStyleWord(true);
        address.setText(parent.getCurrentUser().getAddress());
        email = new JTextField(32);
        email.setText(parent.getCurrentUser().getEmail());
        updateBtn = new JButton("Update Details");
        backbtn = new JButton("Back");

        setupView();
    }

    private void setupView() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 0);


        backbtn.setBounds(0,180,80,30);
        add(backbtn,gc);

        backbtn.addActionListener(ae -> parent.changePanel(new CatalogView(parent)));

        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.insets = new Insets(20, 5, 0, 5);
        gc.fill = GridBagConstraints.NONE;
        add(new JLabel("Name:"),gc);
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridx = 2;
        add(name, gc);

//        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 2;
        add(new JLabel("Email:"),gc);
//        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        add(email, gc);

//        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 3;
        add(new JLabel("Address:"), gc);
//        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        add(address, gc);

//        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 4;
        add(new JLabel("Balance:"), gc);
//        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        add(new JLabel(Double.toString(parent.getCurrentUser().getBalanceDue())), gc);

//        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 5;
        add(new JLabel("Loyalty Points:"), gc);
//        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        add(new JLabel(Integer.toString(parent.getCurrentUser().getLoyaltyRating())), gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 100;

        add(updateBtn, gc);

        updateBtn.addActionListener(e->{
            parent.getCurrentUser().setName(name.getText());
            parent.getCurrentUser().setEmail(email.getText());
            parent.getCurrentUser().setAddress(address.getText());

            //fire update event
            if(listener != null){
                try {
                    listener.profileUpdated(parent.getCurrentUser());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
