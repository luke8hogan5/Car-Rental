package daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import database.Database;
import models.OrderModel;

public class OrderDaoImpl implements OrderDao {

	@Override
	public ResultSet getAllOrdersAdm() {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("SELECT dateCreate,rentDuration,paymentCleared FROM orderTable WHERE vehicleReturned = 0");
	        ResultSet rs = ps.executeQuery();
	        
	        return rs;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	@Override
	public void insertOrder(OrderModel order) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO  orderTable(vehicle_id,user_id,rentDuration,paymentCleared) VALUES (?,?,?,?);");
	        ps.setInt(1, order.getVehicleId());
	        ps.setInt(2, order.getUserId());
	        ps.setInt(3, order.getRentDuration());
	        ps.setBoolean(4, order.getPaymentCleared());
	        ps.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	}
	
	@Override
	public int getOrderId() {
	    Connection conn = Database.getConnection();
	    int id = 0;
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery("SELECT order_id FROM orderTable WHERE order_id =(SELECT MAX(order_id) FROM orderTable);");
	        if(rs.next()){
	        	id = rs.getInt(1);
	        };
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return id;
	}
	
	@Override
	public void updatePayment(int id) {
	    Connection conn = Database.getConnection();

	    String query = "UPDATE orderTable SET paymentCleared=? WHERE order_id="+ id +";";
	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setBoolean(1, true);
	        ps.executeUpdate();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
}
