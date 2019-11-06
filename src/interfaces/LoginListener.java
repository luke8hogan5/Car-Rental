package interfaces;
import java.sql.SQLException;

import models.UserModel;

public interface LoginListener {
	
	public void loginPerformed(UserModel event) throws SQLException;
}

