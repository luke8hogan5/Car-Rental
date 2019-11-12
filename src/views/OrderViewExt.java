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

import views.OrderView;

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
            int loyPoints = 0;
            //int loyPoints = = parent.getCurrentUser().get;

            double vPrice = data.getVehiclePrice();

            buildTable(loyRating, loyPoints, vPrice, userId, vehicleId,rentDuration);
        }


        public void buildTable (int rating, int points, double price, int userId, int vehicleId,int rentDuration){
        	
            backbtn = new JButton("Back");
            double vRentPerDay = ((price / 365) / 2);
            int vRent = (int) Math.round((price / 365) / 2);

            double totalDiscountsPerDay = ((totalDiscounts(rating, vRentPerDay)));
            String netCostPerDay = calNetCost(vRentPerDay, totalDiscountsPerDay);

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
            gc.insets = new Insets(10, 0, 0, 0);
            JTextField loyRating = new JTextField(valueOf(rating), 5);
            loyRating.setEditable(false);
            add(loyRating, gc);

            gc.anchor = GridBagConstraints.LINE_START;
            gc.gridx = 3;
            gc.gridy = 2;
            gc.weightx = 0.2;
            gc.weighty = 1;
            gc.insets = new Insets(10, 10, 10, 120);
            add(new JLabel("Loyalty Points : "), gc);

            JTextField pointsBox = new JTextField(3);
            pointsBox.setEditable(false);
            pointsBox.setText(valueOf(points));
            //makeBox.setVisible(false);
            gc.anchor = GridBagConstraints.LINE_END;
            gc.gridx = 3;
            gc.gridy = 2;
            gc.weightx = 0.4;
            gc.weighty = 1;
            gc.insets = new Insets(10, 0, 10, 90);
            add(pointsBox, gc);
            //makeBox.setVisible(false);

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
            gc.insets = new Insets(10, 0, 30, 0);
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
            if (rating >= 2) {
                deliveryBox.setText("Free Based On Loyalty");
            } else {
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

            //Pass On Values

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

        public String calNetCost(double vCostPerDay, double totalDiscountsPerDay){

            String netCost = "";
            double netCostPerDay = 0.00;
            double userCost = vCostPerDay - totalDiscountsPerDay;

            BigDecimal vCost = BigDecimal.valueOf(vCostPerDay);
            vCost = vCost.setScale(2, RoundingMode.HALF_UP);


            BigDecimal bd = BigDecimal.valueOf(userCost);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            netCostPerDay = bd.doubleValue();

            int totalCostPerDay = (int) Math.round(vCostPerDay);

            netCost += valueOf(vCost.doubleValue()) + " - " + valueOf(totalDiscountsPerDay) + " = " + valueOf(netCostPerDay) + "";
            return netCost;
        }

        double totalDiscounts ( int loyaltyRating, double vCost){
            double totalDiscount = 0.00, rate = 0.00;
            switch (loyaltyRating) {
                case 0:
                    rate = 0;
                    break;
                case 1:
                    rate = vCost * 0.02;
                    break;
                case 2:
                    rate = vCost * 0.04;
                    break;
                case 3:
                    rate = vCost * 0.06;
                    break;
                case 4:
                    rate = vCost * 0.08;
                    break;
                case 5:
                    rate = vCost * 0.10;
                    break;
            }
            BigDecimal bd = BigDecimal.valueOf(rate);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            totalDiscount += bd.doubleValue();
            return totalDiscount;
        }
}
