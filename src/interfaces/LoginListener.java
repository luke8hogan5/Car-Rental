package interfaces;

import java.sql.SQLException;

import models.UserModel;
import views.MasterView;

public interface LoginListener {
	
//	public void loginPerformed(UserModel event) throws SQLException;

    void loginPerformed(String name, String pass, MasterView master) throws SQLException;
}

