package daoImpl;

import dao.AuthenticationDao;
import database.Database;
import models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDaoImpl implements AuthenticationDao {

   /**
	* Extracts User info from DB query results
	* @param rs DB query results
	* @return Extracted user details contained in a user model
	*/
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

   /**
	* Searches for user in DB by username and password
	* @param name User's name.
	* @param password User's password
	* @return User details if user exists, otherwise return null.
	*/
	@Override
	public UserModel validateLogin(String name, String password) {
		Connection conn;
		try {
			Database db = Database.getInstance(); // Needed for JUnit testing
			conn = Database.getConnection();
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

   /**
	* Try to insert new user into DB
	* @param username User's username
	* @param password User's password
	* @param email User's email
	*/
	@Override
	public void registerUser(String username, String password, String email) {
		Connection conn;
		try {
			Database db = Database.getInstance(); // Needed for JUnit testing
			conn = Database.getConnection();


			PreparedStatement ps = conn.prepareStatement("INSERT INTO account (userName,userPassword,email) VALUES (?,?,?);");

			ps.setString(1, username );
			ps.setString(2, password);
			ps.setString(3, email);
			ps.executeUpdate();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

   /**
	* Log user in after they have registered for an account
	* @return User details if user exists, otherwise return null.
	*/
	@Override
	public UserModel loginAfterRegister() {
		Connection conn = Database.getConnection();
		try {
			Database db = Database.getInstance(); // Needed for JUnit testing
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM account WHERE user_id = (SELECT MAX(user_id) FROM account);");

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				UserModel currentUser = extractUserInfo(rs);
				return currentUser;
			}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return null;
	}
}
