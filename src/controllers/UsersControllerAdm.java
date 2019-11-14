package controllers;

import java.sql.*;
import java.util.Vector;

import daoImpl.UserDaoImpl;
import database.Database;
import interfaces.UsersListenerAdm;
import views.UsersViewAdm;

public class UsersControllerAdm implements UsersListenerAdm {
	
	private UsersViewAdm view;
	private UserDaoImpl dao = new UserDaoImpl();
		
	public UsersControllerAdm(UsersViewAdm view) {
		this.view = view;
	}

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

	@Override
		public void updatePerformed(String name, String email, int id) throws SQLException {
			System.out.println("Update event received: ");
			
			dao.updateUserAdm(name, email, id);
		}
		@Override
		public void deletePerformed(int id) throws SQLException {
			System.out.println("Display event received: ");
			
			dao.deleteUserAdm(id);
		}
}

