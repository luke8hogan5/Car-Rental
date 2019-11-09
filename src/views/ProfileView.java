package views;

import interfaces.ProfileListener;
import models.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProfileView extends MasterView implements ActionListener {

    private JTextField name;
    private JTextArea address;
    private JTextField email;
    private JButton updateBtn;

    private UserModel user = new UserModel();

    private ProfileListener listener;

    public ProfileView() {
        super();

        name = new JTextField(32);
        name.setText(user.getName());
        address = new JTextArea(user.getAddress());
        address.setMaximumSize(new Dimension(64, 30));
        address.setLineWrap(true);
        address.setWrapStyleWord(true);
        email = new JTextField(32);
        email.setText(user.getEmail());
        updateBtn = new JButton("Update Details");

        setupView();

        updateBtn.addActionListener(this);
    }

    public void setListener(ProfileListener listener){this.listener = listener;}

    private void setupView() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.WEST;
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
        add(new JLabel(Double.toString(user.getBalance())), gc);

//        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 5;
        add(new JLabel("Loyalty Points:"), gc);
//        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        add(new JLabel(Integer.toString(user.getLoyaltyPts())), gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 100;

        add(updateBtn, gc);

        updateBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        user.setName(name.getText());
        user.setEmail(email.getText());
        user.setAddress(address.getText());

        //fire update event
        if(listener != null){
            try {
                listener.profileUpdated(user);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
