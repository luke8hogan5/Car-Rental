package interfaces;
<<<<<<< HEAD

=======
>>>>>>> 246bbd16297e5ddccc1701a7c289c4e98ae064e2
import java.sql.SQLException;

import models.UserModel;
import views.MasterView;

public interface LoginListener {
	
//	public void loginPerformed(UserModel event) throws SQLException;

    public void loginPerformed(UserModel event, MasterView master) throws SQLException;
}

