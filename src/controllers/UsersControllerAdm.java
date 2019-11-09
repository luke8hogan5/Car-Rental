package controllers;

import java.sql.SQLException;

import interfaces.UsersListenerAdm;
import models.UserModel;
import views.UsersViewAdm;

public class UsersControllerAdm implements UsersListenerAdm {
	
	private UsersViewAdm view;
		
	public UsersControllerAdm(UsersViewAdm view) {
		this.view = view;
	}

		@Override
		public void updatePerformed(UserModel event) throws SQLException {
			System.out.println("Update event received: " + event.getName() + "; " + event.getEmail() + "; " + event.getUserId());
			
		}
		@Override
		public void deletePerformed(UserModel event) throws SQLException {
			System.out.println("Display event received: " + event.getUserId());

		}
		@Override
		public void addPerformed(UserModel event) throws SQLException {
			System.out.println("Display event received: " + event.getName() + "; " + event.getEmail() + "; " + event.getUserId());
			
		}
}

