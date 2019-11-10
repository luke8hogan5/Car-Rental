package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import controllers.Controller;
import controllers.OrderControllerAdm;
import database.Database;
import interfaces.OrderListenerAdm;
import models.OrderModelAdm;

public class OrderViewAdm extends JPanel{
	private MasterView parent;
	private JLabel orderDetails;
	private JButton displayOrders;
	private JScrollPane scrollPane;
	private JTable orderTable;

	private OrderListenerAdm orderAdmListener;
	
	public OrderViewAdm(MasterView parent) throws SQLException {
		super();
		this.parent = parent;
		orderAdmListener = new OrderControllerAdm(this);
		
		displayOrders = new JButton("Display Orders");

		scrollPane = new JScrollPane();
		add(scrollPane);

		Connection conn = Database.getConnection();
		String sql = "SELECT dateCreate, dueDate, paymentCleared FROM orderTable;";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
										
		int count = 0;
		while(rs.next()){
			System.out.println(count);
			count++;
		}
		rs = st.executeQuery();
			
		Object[][] info = new Object[count][3];
		count = 0;
		while(rs.next()){
			info[count][0] = rs.getString("dateCreate");
			info[count][1] = rs.getString("dueDate");
			
			if(rs.getString("paymentCleared").equals("0"))
				info[count][2] = rs.getString("paymentCleared").replace("0", "No");
			else {
				info[count][2] = rs.getString("paymentCleared").replace("1", "Yes");
			}
			count++;
		}
			
		String[] headings = {"VehicleMake","VehicleModel","PaymentCleared"};
			
		orderTable = new JTable(info,headings);
		scrollPane.getViewport().add(orderTable); 
	}
	//displayOrders.addActionListener(this);

//	@Override
//	public void actionPerformed(ActionEvent e) {

//		try {
//			fireOrderAdmEvent(new OrderModelAdm());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}

	public void setOrderAdmListener(OrderListenerAdm orderAdmListener) {
		this.orderAdmListener = orderAdmListener;
	}

	public void fireOrderAdmEvent(OrderModelAdm event) throws SQLException {
		if (orderAdmListener != null) {
			orderAdmListener.orderAdmPerformed(event);
		}
	}
}

