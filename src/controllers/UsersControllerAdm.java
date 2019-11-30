package controllers;

import java.sql.*;
import java.util.Vector;

import daoImpl.UserDaoImpl;
import interfaces.UsersListenerAdm;

public class UsersControllerAdm implements UsersListenerAdm {

	private UserDaoImpl dao = new UserDaoImpl();
		
	public UsersControllerAdm() {
	}

   /**
	* Calls DAO to get data of all users
	* @return User data split into multidimen vector to be inserted into table model
	*/
	@Override
	public Vector<Vector<Object>> getUsers() throws SQLException {

		ResultSet rs;
		rs = dao.getAllUsers();
		
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

   /**
	* Calls DAO to update selected user's details
	* @param name	User's name
	* @param email	User's email
	* @param id		User's ID#
	*/
	@Override
	public void updatePerformed(String name, String email, int id) {
		System.out.println("Update event received: ");

		dao.updateUserAdm(name, email, id);
	}

   /**
	* Calls DAO to delete selected user from database
	* @param id selected user's id
 	*/
	@Override
	public void deletePerformed(int id) {
		System.out.println("Display event received: ");

		dao.deleteUserAdm(id);
	}
}

