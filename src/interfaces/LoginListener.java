package interfaces;
import java.sql.SQLException;

import models.UserModel;
import views.MasterView;

public interface LoginListener {
	
//	public void loginPerformed(UserModel event) throws SQLException;

    public void loginPerformed(UserModel event, MasterView master) throws SQLException;
}

