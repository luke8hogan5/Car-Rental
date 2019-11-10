package interfaces;

import java.sql.SQLException;

import models.UserModel;

public interface UsersListenerAdm {
	
	public void updatePerformed(UserModel event) throws SQLException;
	public void deletePerformed(UserModel event) throws SQLException;
	public void addPerformed(UserModel event) throws SQLException;

	
}

