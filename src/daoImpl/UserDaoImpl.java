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
	    return user;
	}
	
	@Override
	public UserModel getUser() {
		Connection conn = Database.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM account WHERE userType = 1;");
			if(rs.next()){
				extractUserInfo(rs);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}

	@Override
	public List<UserModel> getAllUsers() {
	    Connection conn = Database.getConnection();
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM account WHERE userType = 1");
	        List<UserModel> users = new ArrayList<>();
	        while(rs.next())
	        {
	        	UserModel user = extractUserInfo(rs);
	            users.add(user);
	        }
	        return users;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	@Override
	public UserModel getUserByUserNameAndPassword(String username, String password) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account WHERE userName=? AND userPassword=? AND userType = 1");
	        ps.setString(1, username);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next())
	        {
	        	extractUserInfo(rs);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	@Override
	public void insertUser(UserModel user) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO account VALUES (NULL, ?, ?, ?, ?)");
	        ps.setString(1, user.getName());
	        ps.setString(2, user.getPassword());
	        ps.setString(3, user.getEmail());
	        ps.setInt(4, user.getUserType());
	        ps.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
	}

	@Override
	public void updateUser(UserModel user) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE account SET userName=?, userPassword=?, email=? WHERE user_id=? AND userType = 1");
	        ps.setString(1, user.getName());
	        ps.setString(2, user.getEmail());
	        ps.setInt(3, user.getUserId());
	        ps.executeUpdate();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	@Override
	public void deleteUser(int userId ) {
	    Connection conn = Database.getConnection();
	    try {
	        Statement st = conn.createStatement();
	        st.executeUpdate("DELETE FROM account WHERE user_id=" + userId);

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}


}
