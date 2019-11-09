package interfaces;

import models.UserModel;

import java.sql.SQLException;

public interface ProfileListener {

    public void profileUpdated(UserModel user) throws SQLException;
}
