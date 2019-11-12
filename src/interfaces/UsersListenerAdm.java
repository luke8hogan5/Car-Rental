package interfaces;

import java.sql.SQLException;
import java.util.Vector;

public interface UsersListenerAdm {

	Vector<Vector<Object>> getUsers() throws SQLException;

	void updatePerformed(String name, String email, int id) throws SQLException;

	void deletePerformed(int id) throws SQLException;

	void addPerformed(String name, String email) throws SQLException;
}

