package views;

import controllers.OrderExtController;
import database.Database;
import interfaces.OrderExtListener;
import models.VehicleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

import static java.lang.String.valueOf;

public class OrderViewExt extends JPanel {
    private MasterView parent;
    private OrderExtListener orderExtListener;
    private JButton backbtn;

    public OrderViewExt(MasterView parent, int rentDuration, VehicleModel data) {
            super();
            this.parent = parent;
            orderExtListener = new OrderExtController(this);
            //Send Over Price of Vehicle
            int userId = parent.getCurrentUser().getUserId();
            int vehicleId = data.getVehicleId();
            int loyRating = parent.getCurrentUser().getLoyaltyRating();
            String loyBracket = "";

            if(loyRating > 600){
                  loyBracket = "Diamond User";
            }else if(loyRating > 400) {
                loyBracket = "Platinum User";
            }else if(loyRating > 200) {
                loyBracket = "Gold User";
            }else if(loyRating > 100) {
                loyBracket = "Silver User";
            }else if(loyRating > 50) {
                loyBracket = "Bronze User";
            }else {
                loyBracket = "No Loyalty Tier";
            }
            //int loyPoints = = parent.getCurrentUser().get;

            double vPrice = data.getVehiclePrice();

            buildTable(loyRating, loyBracket, vPrice, userId, vehicleId,rentDuration);
        }


        public void buildTable (int rating, String loyBracket, double price, int userId, int vehicleId,int rentDuration){

            backbtn = new JButton("Back");
            double vRentPerDay = ((price / 365) / 2);
            int vRent = (int) Math.round((price / 365) / 2);

            double totalDiscountsPerDay = ((orderExtListener.totalDiscounts(rating, vRentPerDay)));
            String netCostPerDay = orderExtListener.calNetCost(vRentPerDay, totalDiscountsPerDay);

            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();

            gc.anchor = GridBagConstraints.NORTHWEST;
            gc.gridx = 1;
            gc.gridy = 0;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(0, 0, 0, 0);


            backbtn.setBounds(0,180,80,30);
            add(backbtn,gc);

            backbtn.addActionListener(ae -> parent.changePanel(new CatalogView(parent)));

            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 2;
            gc.gridy = 0;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 30, 0);
            add(new JLabel("Price Summary"), gc);

            gc.anchor = GridBagConstraints.LINE_END;
            gc.gridx = 1;
            gc.gridy = 1;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("Rent or Lease Price Per Day : "), gc);

            JTextField rentBox = new JTextField(10);
            //priceBox.setSize(200,20);
            rentBox.setText(valueOf(vRent));
            rentBox.setEditable(false);
            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 2;
            gc.gridy = 1;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 10, 0);
            add(rentBox, gc);

            gc.anchor = GridBagConstraints.LINE_END;
            gc.gridx = 1;
            gc.gridy = 2;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("Your Loyalty Rating : "), gc);

            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 2;
            gc.gridy = 2;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 10, 0);
            JTextField loyRating = new JTextField(valueOf(rating), 5);
            loyRating.setEditable(false);
            add(loyRating, gc);

            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 3;
            gc.gridy = 2;
            gc.weightx = 0.2;
            gc.weighty = 1;
            gc.insets = new Insets(10, 50, 10, 120);
            add(new JLabel("Loyalty Bracket : "), gc);

            JTextField pointsBox = new JTextField(10);
            pointsBox.setEditable(false);
            pointsBox.setText(loyBracket);
            gc.anchor = GridBagConstraints.CENTER;
            gc.gridx = 3;
            gc.gridy = 2;
            gc.weightx = 0.4;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 10, 100);
            add(pointsBox, gc);

            gc.anchor = GridBagConstraints.LINE_END;
            gc.gridx = 1;
            gc.gridy = 3;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("Total Discount Amount : "), gc);

            JTextField leaseBox = new JTextField(10);
            leaseBox.setText(valueOf(totalDiscountsPerDay));
            leaseBox.setEditable(false);
            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 2;
            gc.gridy = 3;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 10, 0);
            add(leaseBox, gc);

            gc.anchor = GridBagConstraints.LINE_END;
            gc.gridx = 1;
            gc.gridy = 4;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("Net Price Per Day : "), gc);

            JTextField totalBox = new JTextField(10);

            totalBox.setText(netCostPerDay);
            totalBox.setEditable(false);

            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 2;
            gc.gridy = 4;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 10, 0);
            add(totalBox, gc);

            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 2;
            gc.gridy = 5;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 10, 0);
            add(new JLabel("Additonal Fees"), gc);

            //makeBox.setVisible(false);
            gc.anchor = GridBagConstraints.LAST_LINE_END;
            gc.gridx = 1;
            gc.gridy = 6;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("Delivery Fee : "), gc);

            JTextField deliveryBox = new JTextField(13);
            deliveryBox.setEditable(false);
            if (rating >= 100) {
                deliveryBox.setText("Free Based On Loyalty");
            }else{
                deliveryBox.setText("50.00");
            }

            gc.anchor = GridBagConstraints.LAST_LINE_START;
            gc.gridx = 2;
            gc.gridy = 6;
            gc.weightx = 1;
            gc.weighty = 0.2;
            gc.insets = new Insets(10, 0, 10, 10);
            add(deliveryBox, gc);
            deliveryBox.setEditable(false);

            if (rating <= 100){
                gc.anchor = GridBagConstraints.LAST_LINE_START;
                gc.gridx = 3;
                gc.gridy = 6;
                gc.weightx = 1;
                gc.weighty = 0.2;
                gc.insets = new Insets(10, 10, 10, 150);
                add(new JLabel("(Increase loyalty to unlock free delivery)"), gc);
            }
            gc.anchor = GridBagConstraints.LINE_END;
            gc.gridx = 1;
            gc.gridy = 7;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("Deposit Fee : "), gc);

            JTextField priceBox = new JTextField("50", 10);
            //priceBox.setSize(200,20);
            priceBox.setEditable(false);
            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 2;
            gc.gridy = 7;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 10, 0);
            add(priceBox, gc);

            JButton confirmPayment = new JButton("Payment");
            gc.anchor = GridBagConstraints.CENTER;
            gc.gridx = 3;
            gc.gridy = 7;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.insets = new Insets(10, 10, 10, 10);
            add(confirmPayment, gc);

            confirmPayment.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)  {
                    try {
                        fireOrderSubmited(userId,vehicleId,rentDuration,false);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        public void fireOrderSubmited(int userId,int vehicleId,int rentDuration,boolean paymentCleared) throws SQLException {
            if (orderExtListener != null) {
                orderExtListener.orderSubmited(userId, vehicleId,rentDuration,false, parent);
                parent.changePanel(new PaymentView(parent));
            }
        }


}
