package controllers;

import java.sql.*;
import java.util.Vector;

import database.Database;
import interfaces.UsersListenerAdm;
import views.UsersViewAdm;

public class UsersControllerAdm implements UsersListenerAdm {
	
	private UsersViewAdm view;
		
	public UsersControllerAdm(UsersViewAdm view) {
		this.view = view;
	}

	@Override
	public Vector<Vector<Object>> getUsers() throws SQLException {
		Connection conn = Database.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery("select user_id, userName, email, loyaltyRating, balanceDue from account where userType = 1;");

		Vector<Vector<Object>> data = new Vector<>();

		while (rs.next()) {

			Vector<Object> row = new Vector<>();
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
				row.add(rs.getString(i));
			}
			data.add(row);
		}

		return data;
	}

	@Override
		public void updatePerformed(String name, String email, int id) throws SQLException {
			System.out.println("Update event received: ");
			Connection con = Database.getConnection();
			String sql = "UPDATE `account`SET userName='"+name+"',email='"+email+"'WHERE user_id='"+id +"'";
			Statement st = con.createStatement();
			st.execute(sql);
		}
		@Override
		public void deletePerformed(int id) throws SQLException {
			System.out.println("Display event received: ");
			Connection con = Database.getConnection();
			String sql = "DELETE FROM `account` WHERE user_id='"+id+"'";
			Statement st = con.createStatement();
			st.execute(sql);
		}
}

