package interfaces;

import java.sql.SQLException;

import views.MasterView;

public interface LoginListener {

    void loginPerformed(String name, String pass, MasterView master) throws SQLException;
}

