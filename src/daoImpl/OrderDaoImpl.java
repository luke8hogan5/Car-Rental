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
		order.setOrderStatus( rs.getInt("orderStatus") );
		order.setPaymentCleared( rs.getBoolean("paymentCleared") );
		order.setCreatedDate( rs.getDate("dateCreate") );
		order.setDueDate( rs.getDate("dueDate") );
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
	public void insertVehicle(OrderModel order) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO orderTable VALUES (NULL, ?, ?, ?)");
	        ps.setInt(1, order.getOrderStatus());
	        ps.setString(2, order.getCreatedDate());
	        ps.setString(3, order.getDueDate());
	        ps.setBoolean(4, order.getPaymentCleared());
	        ps.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
	}

	@Override
	public void updateVehicle(OrderModel order) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE orderTable SET orderStatus=?, dateCreate=?, dueDate=? , paymentCleared=? WHERE order_id=? AND vehicleReturned = 0");
	        ps.setInt(1, order.getOrderStatus());
	        ps.setString(2, order.getCreatedDate());
	        ps.setString(3, order.getDueDate());
	        ps.setBoolean(4, order.getPaymentCleared());
	        ps.executeUpdate();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	@Override
	public void deleteVehicle(int orderId ) {
	    Connection conn = Database.getConnection();
	    try {
	        Statement st = conn.createStatement();
	        st.executeUpdate("DELETE FROM orderTable WHERE order_id=" + orderId);

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}


}
