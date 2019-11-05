package interfaces;
import java.sql.SQLException;

import models.UserModel;

public interface RegisterListener {
	
	public void registerPerformed(UserModel event) throws SQLException;
}
