package interfaces;
import java.sql.SQLException;

import views.MasterView;

public interface RegisterListener {

	void registerPerformed(String name, String pass, String _email, MasterView master) throws SQLException;
}
