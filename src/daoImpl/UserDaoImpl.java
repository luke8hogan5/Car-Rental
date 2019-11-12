package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import database.Database;
import models.UserModel;

public class UserDaoImpl implements UserDao {

	private UserModel extractUserInfo(ResultSet rs) throws SQLException {
	    UserModel user = new UserModel();
	    user.setUserId( rs.getInt("user_id") );
	    user.setName( rs.getString("userName") );
	    user.setPassword( rs.getString("userPassword") );
	    user.setEmail(rs.getString("email"));
	    return user;
	}

	@Override
	public ResultSet getAllUsers() {
		
		Connection conn = Database.getConnection();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select user_id, userName, email, loyaltyRating, balanceDue from account where userType = 1;");
			
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void updateUserAdm(String name, String email, int id) {
	    Connection conn = Database.getConnection();
	    try {
			Connection con = Database.getConnection();
			String sql = "UPDATE `account`SET userName='"+name+"',email='"+email+"'WHERE user_id='"+id +"'";
			Statement st = con.createStatement();
			st.execute(sql);

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	@Override
	public void deleteUserAdm(int id ) {
	    Connection conn = Database.getConnection();
	    try {
			Connection con = Database.getConnection();
			String sql = "DELETE FROM `account` WHERE user_id='"+id+"'";
			Statement st = con.createStatement();
			st.execute(sql);

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	@Override
	public void updateUserInfo(UserModel user ) {
	    Connection conn = Database.getConnection();
	    try {
	    	PreparedStatement ps = conn.prepareStatement("UPDATE account SET userName=?, email=?, address=? WHERE user_id=?;");
	        
	        ps.setString(1, user.getName());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getAddress());
	        ps.setInt(4, user.getUserId());
	    	ps.execute();
	        
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

}
