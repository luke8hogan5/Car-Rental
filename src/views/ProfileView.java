package views;

import interfaces.ProfListener;
import models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileView extends MasterView implements ActionListener {

    private JTextField name;
    private JTextField address;
    private JTextField email;
    private JTextField balance;
    private JTextField loyaltyTier;

    private Customer user;

    public ProfileView() {
        super();

        name = new JTextField();
        address = new JTextField();
        email = new JTextField();
        balance = new JTextField();
        loyaltyTier = new JTextField();

        setupView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setListener(ActionListener profListener){}

    private void setupView() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(name, gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
//        gc.gridx = 1;
        gc.gridy = 2;
//        gc.weightx = 1;
//        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(email, gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
//        gc.gridx = 1;
        gc.gridy = 3;
//        gc.weightx = 1;
//        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(address, gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
//        gc.gridx = 1;
        gc.gridy = 4;
//        gc.weightx = 1;
//        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(loyaltyTier, gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
//        gc.gridx = 1;
        gc.gridy = 5;
//        gc.weightx = 1;
//        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(balance, gc);


    }
}
