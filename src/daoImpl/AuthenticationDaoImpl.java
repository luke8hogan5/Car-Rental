package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.AuthenticationDao;
import database.Database;
import models.UserModel;

public class AuthenticationDaoImpl implements AuthenticationDao {
	
	private UserModel extractUserInfo(ResultSet rs) throws SQLException {
	    UserModel user = new UserModel(
	    rs.getInt(1),
	    rs.getInt(2),
	    rs.getString(3),
	    rs.getString(5),
	    rs.getString(6),
	    rs.getInt(7),
	    rs.getDouble(8));
	    return user;
	}
	
	@Override
	public UserModel validateLogin(String name, String password) {
		Connection conn = Database.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM account WHERE userName = ? AND userPassword = ?;");
			ps.setString(1,name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				UserModel user = extractUserInfo(rs);
				return user;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void registerUser(String username, String password, String email) {
		Connection conn = Database.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO account`(userName`,`userPassword`, email) VALUES (?,?,?);");
			ResultSet rs = ps.executeQuery();
		    
			if(rs.next()){
				ps.setString(1, username );
			    ps.setString(2, password);
			    ps.setString(3, email);
			    ps.executeUpdate();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
	}
	@Override
	public void loginAfterRegister() {
		Connection conn = Database.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM account WHERE user_id (" +
					"SELECT MAX(user_id) FROM account" + ");");
			ResultSet rs = ps.executeQuery();
		    
			if(rs.next()){
				extractUserInfo(rs);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
	}
}
