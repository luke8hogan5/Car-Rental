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

	private OrderModel extractUserInfo(ResultSet rs) throws SQLException {
		OrderModel order = new OrderModel();
		order.setPaymentCleared( rs.getBoolean("paymentCleared") );
		order.setCreatedDate( rs.getDate("dateCreate") );
		order.setRentDuration( rs.getInt("rentDuration") );
	    return order;
	}
	
	@Override
	public OrderModel getOrder() {
		Connection conn = Database.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM orderTable WHERE vehicleReturned = 0;");
			if(rs.next()){
				extractUserInfo(rs);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}

	@Override
	public List<OrderModel> getAllOrders() {
	    Connection conn = Database.getConnection();
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM orderTable WHERE vehicleReturned = 0");
	        List<OrderModel> orders = new ArrayList<>();
	        while(rs.next())
	        {
	        	OrderModel order = extractUserInfo(rs);
	        	orders.add(order);
	        }
	        return orders;
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
	public void insertOrderAdm(OrderModel order) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO  orderTable(rentDuration,paymentCleared) VALUES (?,?);");
	        ps.setInt(3, order.getRentDuration());
	        ps.setBoolean(4, order.getPaymentCleared());
	        ps.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
	}

	@Override
	public void updateOrderAdm(OrderModel order) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE orderTable SET orderStatus=?, dateCreate=?, dueDate=? , paymentCleared=? WHERE order_id=? AND vehicleReturned = 0");
	        ps.setString(1, order.getCreatedDate());
	        ps.setInt(2, order.getRentDuration());
	        ps.setBoolean(3, order.getPaymentCleared());
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
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE orderTable SET paymentCleared=? WHERE order_id=" + id + ";");
	        ps.setBoolean(1, true);
	        ps.executeUpdate();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	@Override
	public void deleteOrder(int orderId ) {
	    Connection conn = Database.getConnection();
	    try {
	        Statement st = conn.createStatement();
	        st.executeUpdate("DELETE FROM orderTable WHERE order_id=" + orderId);

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}


}
