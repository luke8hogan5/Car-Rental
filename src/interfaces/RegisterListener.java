package interfaces;
import java.sql.SQLException;

import models.UserModel;
import views.MasterView;

public interface RegisterListener {
	
	public void registerPerformed(UserModel event, MasterView master) throws SQLException;
}
