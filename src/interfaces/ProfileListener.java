package interfaces;

import models.Customer;
import models.UserModel;

import java.sql.SQLException;

public interface ProfileListener {

    public void profileUpdated(Customer user) throws SQLException;
}
