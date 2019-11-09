package views;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import database.Database;
import interfaces.OrderListener;
import models.VehicleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.String.valueOf;

public class OrderViewExt extends MasterView implements ActionListener {

    private interfaces.OrderListener OrderListener;
    public String[] vehicleModels = new String[10];
    public JComboBox modelList;

    public OrderViewExt() {
        super();
        //Send Over Price of Vehicle
        int userId = 1 , vehicleId = 5;
        int loyRating = 0 , loyPoints = 0;
        double vPrice = 0.0;
        Connection conn = Database.getConnection();
        String queryPoints = "SELECT `loyaltyRating`,`loyaltyPoints` FROM  `discounts` WHERE `user_id`=\""+userId+"\";";
        String queryPrice = "SELECT `vehiclePrice` FROM  `vehicle` WHERE `vehicle_id`=\""+vehicleId+"\";";

        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(queryPoints);
            //int i = 0;
            while (rs.next()) {
                loyRating = rs.getInt("loyaltyRating");
                loyPoints = rs.getInt("loyaltyPoints");
                //i++;
            }
            ResultSet vp = st.executeQuery(queryPrice);
            while(vp.next()){
                vPrice = vp.getDouble("vehiclePrice");
            }
            buildTable(loyRating,loyPoints,vPrice);
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public void buildTable(int rating,int points,double price) {

        double vRentPerDay = ((price/365)/2);
        int vRent = (int) Math.round((price / 365)/2);
        int rentDuration = 50;

        double totalDiscountsPerDay = ((totalDiscounts(rating,vRentPerDay,rentDuration)));
        String netCostPerDay = calNetCost(vRentPerDay,totalDiscountsPerDay,rentDuration);


        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.PAGE_START;
        gc.gridx = 1;
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
        add(new JLabel("Rent or Lease Price Per Day :"), gc);

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
        add(new JLabel("Your Loyalty Rating :"), gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 0, 0);
        JTextField loyRating = new JTextField(valueOf(rating),5);
        loyRating.setEditable(false);
        add(loyRating,gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.gridx = 1;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Current Loyalty Points :"), gc);

        JTextField pointsBox = new JTextField(10);
        pointsBox.setEditable(false);
        pointsBox.setText(valueOf(points));
        //makeBox.setVisible(false);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(pointsBox, gc);
        //makeBox.setVisible(false);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 4;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Total Discount Amount : "), gc);

        JTextField leaseBox = new JTextField(10);
        leaseBox.setText(valueOf(totalDiscountsPerDay));
        leaseBox.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 4;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 0, 10, 0);
        add(leaseBox, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 5;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Net Price Per Day : "), gc);

        JTextField totalBox = new JTextField(10);

        totalBox.setText(netCostPerDay);
        totalBox.setEditable(false);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 0, 10, 0);
        add(totalBox, gc);

        //makeBox.setVisible(false);
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.gridx = 1;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Delivery Fee : "), gc);

        int deliveryCost = 0;

        JTextField deliveryBox = new JTextField(10);
        deliveryBox.setEditable(false);

        if(rating >= 2) {
            deliveryBox.setText("Free Loyalty");
        }else{
            deliveryCost = 50;
            deliveryBox.setText("50.00");
        }

        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.insets = new Insets(10, 10, 10, 10);
        add(deliveryBox,gc);
        deliveryBox.setEditable(false);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 7;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        add(new JLabel("Deposit Fee : "), gc);

        JTextField priceBox = new JTextField("50",10);
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
            public void actionPerformed(ActionEvent e) {

            }
        } );


    }
    public String calNetCost(double vCostPerDay , double totalDiscountsPerDay , int rentDuration){

            String netCost = "";
            double netCostPerDay = 0.00;
            //int discountPerDay = (int) Math.round(totalDiscounts / rentDuration);
            double userCost = vCostPerDay - totalDiscountsPerDay;

            BigDecimal bd = BigDecimal.valueOf(userCost);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            netCostPerDay = bd.doubleValue();

            int totalCostPerDay = (int) Math.round(vCostPerDay);

            netCost += valueOf(totalCostPerDay) +  " - " + valueOf(totalDiscountsPerDay) + " = " + valueOf(netCostPerDay) +  "" ;
            return netCost;
    }

    double totalDiscounts(int loyaltyRating, double vCost , int rentDuration){
        double totalDiscount = 0.00 , rate = 0.00;
            switch (loyaltyRating) {
                 case 0:
                    rate = 0;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Actions Yeet");
    }
////			fireLoginEvent(new RegisterModel(name, password));
////		} else {
////			JOptionPane.showMessageDialog(this, "User not found.",
////					"Error", JOptionPane.WARNING_MESSAGE);
////		}
////	}

    public void setOrderListener(OrderListener orderListener) {
        this.OrderListener = orderListener;
    }

    public void fireLoginEvent(VehicleModel event) throws SQLException {
        if (OrderListener != null) {
            OrderListener.orderPerformed(event);
        }
    }
}
